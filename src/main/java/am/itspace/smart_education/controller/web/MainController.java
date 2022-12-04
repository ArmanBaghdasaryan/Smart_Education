package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.entity.Role;
import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.service.UserService;
import am.itspace.smart_education.common.service.serviceImpl.LessonServiceImpl;
import am.itspace.smart_education.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final LessonServiceImpl lessonService;
    private final UserService userService;


    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "web/accessDenied";
    }


    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            if (user.getRole() == Role.ADMIN) {
                return "redirect:/admin/admin";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/")
    public String home(ModelMap modelMap) {
        List<Lesson> allLesson = lessonService.findAll();
        List<User> allTeacher = userService.findByRole(Role.TEACHER);
        modelMap.addAttribute("courses", allLesson);
        modelMap.addAttribute("teachersHome", allTeacher);
        return "web/index";
    }
}



