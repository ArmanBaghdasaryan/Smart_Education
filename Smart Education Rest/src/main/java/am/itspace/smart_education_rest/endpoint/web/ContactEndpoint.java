package am.itspace.smart_education_rest.endpoint.web;

import am.itspace.smart_education_common.entity.Role;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.model.EmailDetail;
import am.itspace.smart_education_common.service.MailService;
import am.itspace.smart_education_common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactEndpoint {

    private final UserService userService;
    private final MailService mailService;


    @PostMapping("/contact/email")
    public ResponseEntity<?> sendEmailToAdmin(@RequestBody EmailDetail emailDetail) {
        List<User> admins = userService.findByRole(Role.ADMIN);
        mailService.sendMail(emailDetail, admins);
        return ResponseEntity.ok().build();
    }
}
