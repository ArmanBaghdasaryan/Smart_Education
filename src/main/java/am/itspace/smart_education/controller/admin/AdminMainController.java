package am.itspace.smart_education.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminMainController {

    @GetMapping("/admin")
    public String adminPage() {
        return "admin/admin";
    }

}
