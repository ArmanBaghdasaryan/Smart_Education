package am.itspace.smart_education_web.controller.web;

import am.itspace.smart_education_common.service.UserService;
import am.itspace.smart_education_common.dto.CreateUserDto;
import am.itspace.smart_education_common.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/user/register")
    public String addUser() {
        return "web/index";
    }

    @PostMapping("/user/register")
    public String addUser(@ModelAttribute CreateUserDto userDto,
                          @RequestParam("profPic") MultipartFile file,
                          ModelMap modelMap) throws IOException, MessagingException {
        if (userService.checkUserEmailAndUserImage(userMapper.map(userDto), file, modelMap)) {
            return "redirect:/user/register";
        }
        userService.save(userMapper.map(userDto), file);
        return "redirect:/";
    }

    @GetMapping("/user/verify")
    public String verifyUser(@RequestParam("email") String email,
                             @RequestParam("token") String token) throws Exception {
        userService.verifyUser(email, token);
        return "redirect:/";
    }
}