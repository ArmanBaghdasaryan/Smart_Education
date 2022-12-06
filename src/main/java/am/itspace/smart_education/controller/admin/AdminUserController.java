package am.itspace.smart_education.controller.admin;


import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.service.UserService;
import am.itspace.smart_education.dto.CreateUserDto;
import am.itspace.smart_education.dto.RequestAdminUserDto;
import am.itspace.smart_education.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@RequestMapping("/admin/user")
@Controller
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService userService;
    private final UserMapper mapper;


    @GetMapping("/add")
    public String addUser() {
        return "admin/addUser";
    }


    @PostMapping("/add")
    public String addUser(@ModelAttribute CreateUserDto userDto,
                          @RequestParam("profPic") MultipartFile file,
                          ModelMap modelMap) throws IOException, MessagingException {
        User user = mapper.map(userDto);
        if (userService.checkUserEmailAndUserImage(user, file, modelMap)) {
            return "admin/addUser";
        }
        user.setEnable(true);
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
        Optional<User> byId = Optional.ofNullable(userService.findById(id));
        if (byId.isEmpty()) {
            return "redirect:/admin/user";
        }
        User user = byId.get();
        modelMap.addAttribute("users", user);
        userService.checkUserEmailAndUserImage(user, file, modelMap);
        return "admin/editUser";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute RequestAdminUserDto userDto,
                         @RequestParam("profPic") MultipartFile file) throws IOException {
        userService.updateUser(mapper.mapFromDto(userDto), file);
        return "redirect:/admin/user";
    }

    @GetMapping
    public String allUsers(@RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           ModelMap modelMap) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<User> employeesPage = userService.findUsersWithPage(PageRequest.of(currentPage - 1, pageSize));
        modelMap.addAttribute("users", employeesPage);
        int totalPages = employeesPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin/admin_user";
    }

}