package am.itspace.smart_education.controller.admin;


import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;

    @GetMapping("/admin/user")
    public String user(ModelMap modelMap) {
        List<User> allUsers = userService.findAll();
        modelMap.addAttribute("users", allUsers);
        return "admin/admin_user";
    }

    @GetMapping("/admin/user/add")
    public String addUser() {
        return "addUser";
    }


    @PostMapping("/admin/user/add")
    public String addUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin/user";
    }


    @GetMapping("/admin/user/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteById(id);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/update")
    public String update(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin/user";
    }


}
