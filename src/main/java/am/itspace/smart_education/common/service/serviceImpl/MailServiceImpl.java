package am.itspace.smart_education.common.service.serviceImpl;

import am.itspace.smart_education.common.entity.Role;
import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.model.EmailDetail;
import am.itspace.smart_education.common.service.MailService;
import am.itspace.smart_education.common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableAsync
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final UserService userService;



    @Async
    public void sendMail(EmailDetail emailDetail) {
        List<User> users = userService.findByRole(Role.ADMIN);
        String adminEmail = users.get(0).getEmail();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String to = adminEmail;
        mailMessage.setTo(to);
        mailMessage.setSubject(emailDetail.getEmail());
        String message = String.format("You got message from email: %s name: %s surname: %s phone: %s \n MESSAGE: %s \n",
                emailDetail.getEmail(), emailDetail.getFirstName(), emailDetail.getLastName(), emailDetail.getPhone(), emailDetail.getMessage()
        );
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }
}
