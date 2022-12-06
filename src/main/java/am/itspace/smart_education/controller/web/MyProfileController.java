package am.itspace.smart_education.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MyProfileController {


    @GetMapping("/my_profile")
    public String contactPage() {
        return "web/myProfile";
    }
}
