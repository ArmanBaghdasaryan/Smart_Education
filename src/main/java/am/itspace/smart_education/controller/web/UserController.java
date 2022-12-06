package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.entity.Role;
import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.repository.LessonRepository;
import am.itspace.smart_education.common.repository.UserRepository;
import am.itspace.smart_education.common.service.serviceImpl.LessonServiceImpl;
import am.itspace.smart_education.common.service.serviceImpl.UserServiceImpl;
import am.itspace.smart_education.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import am.itspace.smart_education.common.service.UserService;
import am.itspace.smart_education.dto.CreateUserDto;
import am.itspace.smart_education.mapper.UserMapper;
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
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final LessonServiceImpl lessonService;
    private final PasswordEncoder passwordEncoder;
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
            return "web/fragments/fragment";
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