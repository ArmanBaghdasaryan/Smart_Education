package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.entity.Role;
import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "web/index";
    }


    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "web/accessDenied";
    }


    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            if (user.getRole() == Role.ADMIN) {
                return "redirect:/";
            } else if (user.getRole() == Role.USER) {
                return "redirect:/";
            } else if (user.getRole() == Role.TEACHER) {
                return "redirect:/";
            }
        }
        return "redirect:/";
    }

}



