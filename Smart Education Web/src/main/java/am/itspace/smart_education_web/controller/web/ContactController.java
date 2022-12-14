package am.itspace.smart_education_web.controller.web;

import am.itspace.smart_education_common.entity.Role;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.model.EmailDetail;
import am.itspace.smart_education_common.service.MailService;
import am.itspace.smart_education_common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final MailService mailService;
    private final UserService userService;

    @GetMapping("/contact")
    public String contactPage() {
        return "web/contact";
    }


    @PostMapping("/contact/email")
    public String sendEmailToAdmin(EmailDetail emailDetail) {
        List<User> admins = userService.findByRole(Role.ADMIN);
        mailService.sendMail(emailDetail, admins);
        return "web/contact";
    }

}
