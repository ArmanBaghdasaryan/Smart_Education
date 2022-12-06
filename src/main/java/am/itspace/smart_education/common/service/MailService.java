package am.itspace.smart_education.common.service;

import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.model.EmailDetail;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import java.util.List;


public interface MailService {

    @Async
    void sendMail(EmailDetail emailDetail, List<User> admins);

    @Async
    void sendHtmlEmail(String to, String subject, String text) throws MessagingException;
}
