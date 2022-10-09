package am.itspace.smart_education.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class AboutController {

    @GetMapping("/about_us")
    public String aboutPage(){
        return "web/about";
    }
}
