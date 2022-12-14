package am.itspace.smart_education_rest.service.impl;

import am.itspace.smart_education_common.entity.Role;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.repository.UserRepository;
import am.itspace.smart_education_common.service.MailService;
import am.itspace.smart_education_rest.service.UserServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImplV2 implements UserServiceV2 {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final MailService mailService;
    @Value("${smart.education.images.folder}")
    private String folderPath;

    public User save(User user) throws MessagingException {

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
        return user;
    }

    public void checkedImage(User user, MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File newFile = new File(folderPath + File.separator + fileName);
        file.transferTo(newFile);
        user.setPicture(fileName);
    }


    public boolean checkUserEmailAndUserImage(User user, MultipartFile file) {
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail.isPresent()) {
            return true;
        }
        if (!file.isEmpty() && file.getSize() > 0) {
            return file.getContentType() != null && !file.getContentType().contains("image");
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

    public User verifyUser(String email, String token) throws Exception {
        Optional<User> userOptional = userRepository.findByEmailAndVerifyToken(email, token);

        if (userOptional.isEmpty()) {
            throw new Exception("User Does not exists with email and token");
        }
        User user = userOptional.get();
        if (user.isEnable()) {
            throw new Exception("User already enabled");
        }
        user.setEnable(true);
        user.setVerifyToken(null);
        return userRepository.save(user);
    }
}
