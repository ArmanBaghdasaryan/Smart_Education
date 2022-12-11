package am.itspace.smart_education_web.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AboutController {

    @GetMapping("/about_us")
    public String aboutPage() {
        return "web/about";
    }
}
