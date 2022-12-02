package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.entity.Role;
import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.service.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class UserController {


    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/user/register")
    public String addUser() {
        return "web/index";
    }

    @PostMapping("/user/register")
    public String addUser(@ModelAttribute User user,
                          @RequestParam("profPic") MultipartFile file,
                          ModelMap modelMap) throws IOException {
        if (userService.checkUserEmailAndUserImage(user, file, modelMap)) {
            return "web/fragments/fragment";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userService.save(user, file);
        return "redirect:/";
    }

}
