package am.itspace.smart_education.common.service;

import am.itspace.smart_education.common.model.EmailDetail;
import org.springframework.scheduling.annotation.Async;



public interface MailService {

    @Async
    void sendMail(EmailDetail emailDetail);
}
