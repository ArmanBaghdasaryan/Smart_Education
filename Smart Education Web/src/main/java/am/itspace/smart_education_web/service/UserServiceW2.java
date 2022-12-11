package am.itspace.smart_education_web.service;

import am.itspace.smart_education_common.entity.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

public interface UserServiceW2 {
    void save(User user, MultipartFile file) throws IOException, MessagingException;
    void updateUser(User user, MultipartFile file) throws IOException;
    void checkedImage(User user, MultipartFile file) throws IOException;
    boolean checkUserEmailAndUserImage(User user, MultipartFile file, ModelMap modelMap);
    User findById(int id);

}
