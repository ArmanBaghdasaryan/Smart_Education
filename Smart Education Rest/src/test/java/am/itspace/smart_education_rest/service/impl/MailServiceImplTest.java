package am.itspace.smart_education_rest.service.impl;

import am.itspace.smart_education_common.model.EmailDetail;
import am.itspace.smart_education_common.service.impl.MailServiceImpl;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MailServiceImplTest {

    @Autowired
    private MailServiceImpl mailService;

    @Test
    void testSendMail() {

        EmailDetail emailDetail = new EmailDetail("Doe", "Jane", "jane.doe@example.org", "4105551212",
                "Not all who wander are lost");

        mailService.sendMail(emailDetail, new ArrayList<>());
    }
}
