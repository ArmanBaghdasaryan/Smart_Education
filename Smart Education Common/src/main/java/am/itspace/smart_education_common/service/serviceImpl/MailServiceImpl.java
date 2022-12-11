package am.itspace.smart_education_common.service.serviceImpl;

import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.model.EmailDetail;
import am.itspace.smart_education_common.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
@EnableAsync
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Async
    public void sendMail(EmailDetail emailDetail, List<User> admins) {
        String adminEmail = admins.get(0).getEmail();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(adminEmail);
        mailMessage.setSubject(emailDetail.getEmail());
        String message = String.format("You got message from email: %s name: %s surname: %s phone: %s \n MESSAGE: %s \n",
                emailDetail.getEmail(), emailDetail.getFirstName(), emailDetail.getLastName(), emailDetail.getPhone(), emailDetail.getMessage()
        );
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }

    @Async
    public void sendHtmlEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage mailMsg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMsg, false);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        javaMailSender.send(mailMsg);
    }
}
