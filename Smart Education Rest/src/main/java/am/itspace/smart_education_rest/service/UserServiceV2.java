package am.itspace.smart_education_rest.service;

import am.itspace.smart_education_common.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

public interface UserServiceV2 {

    User save(User user) throws IOException, MessagingException;

    void checkedImage(User user, MultipartFile file) throws IOException;

    boolean checkUserEmailAndUserImage(User user, MultipartFile file);

    User verifyUser(String email, String token) throws Exception;

}
