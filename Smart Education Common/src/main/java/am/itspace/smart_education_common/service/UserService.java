package am.itspace.smart_education_common.service;

import am.itspace.smart_education_common.entity.Role;
import am.itspace.smart_education_common.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    void save(User user, MultipartFile file) throws IOException, MessagingException;

    void deleteById(int id);

    Optional<User> findByUserId(int id);
    User findById(int id) throws EntityNotFoundException;

    void updateUser(User user, MultipartFile file) throws IOException;

    byte[] getUserImage(String fileName) throws IOException;

    void checkedImage(User user, MultipartFile file) throws IOException;

    List<User> findByRole(Role role);

    boolean checkUserEmailAndUserImage(User user, MultipartFile file, ModelMap modelMap);

    void verifyUser(String email, String token) throws Exception;

    Page<User> findUsersWithPage(Pageable pageable);

    Optional<User> findByEmail(String email);

    void saveUser (User user);

}

