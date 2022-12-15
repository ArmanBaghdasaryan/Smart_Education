package am.itspace.smart_education_web.controller.web;

import am.itspace.smart_education_common.entity.Role;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static am.itspace.smart_education_common.entity.Role.USER;

@Controller
@RequiredArgsConstructor
public class AboutController {

    @GetMapping("/about_us")
    public String aboutPage(User user) {
            return "web/about";
    }
}
