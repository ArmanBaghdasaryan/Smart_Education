package am.itspace.smart_education.common.service;

import am.itspace.smart_education.common.entity.Role;
import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Value("${smart.education.images.folder}")
    private String folderPath;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user, MultipartFile file) throws IOException {
        checkedImage(user, file);
        if (user.getRole() == null) {
            user.setRole(Role.ADMIN);
        }
        userRepository.save(user);

    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);

    }

    public void updateUser(User user, MultipartFile file) throws IOException {
        checkedImage(user, file);
        userRepository.save(user);
    }

    public byte[] getUserImage(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private void checkedImage(User user, MultipartFile file) throws IOException {
        if (!file.isEmpty() && file.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File newFile = new File(folderPath + File.separator + fileName);
            file.transferTo(newFile);
            user.setPicture(fileName);
        }
    }



        public List<User> findByRoleTeacher() {
            return userRepository.findAllByRole(Role.TEACHER);
        }
}
