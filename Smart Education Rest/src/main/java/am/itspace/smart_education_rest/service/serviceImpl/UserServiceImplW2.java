package am.itspace.smart_education_rest.service.serviceImpl;

import am.itspace.smart_education_common.entity.Role;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.repository.UserRepository;
import am.itspace.smart_education_common.service.MailService;
import am.itspace.smart_education_rest.service.UserServiceW2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImplW2 implements UserServiceW2 {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final MailService mailService;
    @Value("${smart.education.images.folder}")
    private String folderPath;

    public void save(User user, MultipartFile file) throws IOException, MessagingException {
        if (!file.isEmpty() && file.getSize() > 0) {
            checkedImage(user, file);
        }
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnable(user.getRole() != Role.USER);
        user.setVerifyToken(UUID.randomUUID().toString());
        userRepository.save(user);
        mailService.sendHtmlEmail(user.getEmail(), "Please verify your email",
                "Hi " + user.getName() + "\n" +
                        "Please verify your account by clicking on this link " +
                        "<a href=\"http://localhost:8080/user/verify?email=" + user.getEmail() + "&token=" + user.getVerifyToken() + "\">Activate</a>"
        );
    }
    public void updateUser(User user, MultipartFile file) throws IOException {
        if (user.getPassword() == null) {
            Optional<User> byId = Optional.ofNullable(findById(user.getId()));
            byId.ifPresent((userFromDb) -> user.setPassword(userFromDb.getPassword()));
        }
        if (!file.isEmpty() && file.getSize() > 0) {
            checkedImage(user, file);
        } else {
            Optional<User> byId = Optional.ofNullable(findById(user.getId()));
            byId.ifPresent((userFromDb) -> user.setPicture(userFromDb.getPicture()));
        }
        userRepository.save(user);
    }
    public void checkedImage(User user, MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File newFile = new File(folderPath + File.separator + fileName);
        file.transferTo(newFile);
        user.setPicture(fileName);
    }
    public boolean checkUserEmailAndUserImage(User user, MultipartFile file,
                                              ModelMap modelMap) {
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail.isPresent()) {
            modelMap.addAttribute("msgEmail", "Email already in use");
            return true;
        }
        if (!file.isEmpty() && file.getSize() > 0) {
            if (file.getContentType() != null && !file.getContentType().contains("image")) {
                modelMap.addAttribute("msgFile", "Please choose only image");
                return true;
            }
        }
        return false;
    }

    public User findById(int id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            throw new RuntimeException("User with id" + id + " does not exists");
        }
        return byId.get();
    }
}
