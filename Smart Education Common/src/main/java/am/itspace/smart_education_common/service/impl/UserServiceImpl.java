package am.itspace.smart_education_common.service.impl;

import am.itspace.smart_education_common.entity.Role;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.repository.UserRepository;
import am.itspace.smart_education_common.service.MailService;
import am.itspace.smart_education_common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final MailService mailService;
    @Value("${smart.education.images.folder}")
    private String folderPath;

    public List<User> findAll() {
        return userRepository.findAll();
    }

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

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public User findById(int id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            throw new RuntimeException("User with id" + id + " does not exists");
        }
        return byId.get();
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

    public byte[] getUserImage(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }

    public void checkedImage(User user, MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File newFile = new File(folderPath + File.separator + fileName);
        file.transferTo(newFile);
        user.setPicture(fileName);
    }

    public List<User> findByRole(Role role) {
        return userRepository.findAllByRole(role);
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

    public void verifyUser(String email, String token) throws Exception {
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
        userRepository.save(user);
    }

    public Page<User> findUsersWithPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
