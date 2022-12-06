package am.itspace.smart_education.common.service;

import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.entity.Role;
import am.itspace.smart_education.common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    List<User> findAll();

    void save(User user, MultipartFile file) throws IOException, MessagingException;

    void deleteById(int id);

    User findById(int id);

    void updateUser(User user, MultipartFile file) throws IOException;

    byte[] getUserImage(String fileName) throws IOException;

    void checkedImage(User user, MultipartFile file) throws IOException;

    List<User> findByRole(Role role);

    boolean checkUserEmailAndUserImage(@ModelAttribute User user,
                                       @RequestParam("profPic") MultipartFile file,
                                       ModelMap modelMap);
}
    void verifyUser(String email, String token) throws Exception;

    Page<User> findUsersWithPage(Pageable pageable);
}

