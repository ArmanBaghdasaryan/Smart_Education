package am.itspace.smart_education.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeachersPageController {

    @GetMapping("/teachersPage")
    public String teachersPage(){
        return "web/teachersPage";
    }
}