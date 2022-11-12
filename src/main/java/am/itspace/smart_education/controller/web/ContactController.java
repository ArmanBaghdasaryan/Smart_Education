package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.model.EmailDetail;
import am.itspace.smart_education.common.service.MailService;
import am.itspace.smart_education.common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final MailService mailService;

    @GetMapping("/contact")
    public String contactPage() {
        return "web/contact";
    }


    @PostMapping("/contact/email")
    public String sendEmailToAdmin(EmailDetail emailDetail) {

        mailService.sendMail(emailDetail);
        return "web/contact";
    }

}
