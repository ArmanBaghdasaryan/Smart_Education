package am.itspace.smart_education.controller.admin;


import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;

    @GetMapping
    public String showAllUsers(ModelMap modelMap) {
        List<User> allUsers = userService.findAll();
        modelMap.addAttribute("users", allUsers);
        return "admin/admin_user";
    }

    @GetMapping("/add")
    public String addUser() {
        return "admin/addUser";
    }


    @PostMapping("/add")
    public String addUser(@ModelAttribute User user,
                          @RequestParam("profPic") MultipartFile file,
                          ModelMap modelMap) throws IOException {
        if (checkUserEmailAndUserImage(user, file, modelMap)) return "admin/addUser";
        userService.save(user, file);
        return "redirect:/admin/user";
    }

    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return userService.getUserImage(fileName);
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/admin/user";
    }

    @GetMapping("/update/{id}")
    public String update(ModelMap modelMap,
                         @PathVariable("id") int id,
                         MultipartFile file) {
        Optional<User> byId = userService.findById(id);
        if (byId.isEmpty()) {
            return "redirect:/admin/user";
        }
        User user = byId.get();
        modelMap.addAttribute("users", user);
        checkUserEmailAndUserImage(user, file, modelMap);
        return "admin/editUser";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute User user,
                         @RequestParam("profPic") MultipartFile file) throws IOException {
        userService.updateUser(user, file);
        return "redirect:/admin/user";
    }

    private boolean checkUserEmailAndUserImage(@ModelAttribute User user,
                                               @RequestParam("profPic") MultipartFile file,
                                               ModelMap modelMap) {
        Optional<User> byEmail = userService.findByEmail(user.getEmail());
        if (byEmail.isPresent()) {
            modelMap.addAttribute("msgEmail", "Email already in use");
            return true;
        }
        if (!file.isEmpty() && file.getSize() > 0) {
            if (file.getContentType() != null && !file.getContentType().contains("image")) {
                modelMap.addAttribute("msgFile", "Please choose only image");
                return true;
            }
        }
        return false;
    }
}