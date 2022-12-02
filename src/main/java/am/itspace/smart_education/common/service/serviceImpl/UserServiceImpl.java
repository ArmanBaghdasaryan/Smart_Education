package am.itspace.smart_education.common.service.serviceImpl;

import am.itspace.smart_education.common.entity.Role;
import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.repository.UserRepository;
import am.itspace.smart_education.common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    @Value("${smart.education.images.folder}")
    private String folderPath;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user, MultipartFile file) throws IOException {
        if (!file.isEmpty() && file.getSize() > 0) {
            checkedImage(user, file);
        }
        if (user.getRole() == null) {
            user.setRole(Role.ADMIN);
        }
        userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public User findById(int id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
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

    public Optional<User> findByEmail(String email) {

        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new RuntimeException("Email with " + email + " email does not exists");
        }
        return byEmail;
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

    public boolean checkUserEmailAndUserImage(@ModelAttribute User user,
                                              @RequestParam("profPic") MultipartFile file,
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
}
