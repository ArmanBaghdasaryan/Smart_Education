package am.itspace.smart_education_common.service.impl;

import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.repository.LessonRepository;
import am.itspace.smart_education_common.repository.UserRepository;
import am.itspace.smart_education_common.security.CurrentUser;
import am.itspace.smart_education_common.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    @Value("${smart.education.images.folder}")
    private String folder;

    public Optional<Lesson> findById(int id) {
        return lessonRepository.findById(id);
    }

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }


    public void save(Lesson lesson, MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty() && multipartFile.getSize() > 0) {
            checkedImage(lesson, multipartFile);
        }
        lessonRepository.save(lesson);
    }

    public void deleteById(int id) {
        lessonRepository.deleteById(id);
    }

    public void subscribe(int lessonId, int userId) {
        Optional<Lesson> lessonById = lessonRepository.findById(lessonId);
        Optional<User> userById = userRepository.findById(userId);

        userById.ifPresent(u -> {
            lessonById.ifPresent(l -> {
                u.getLessons().add(l);
                userRepository.save(u);
            });
        });
    }

    public void deleteSubscribe(int lessonId, int userId) {
        Optional<Lesson> lessonById = lessonRepository.findById(lessonId);
        Optional<User> userById = userRepository.findById(userId);
        lessonById.ifPresent(l -> {
            userById.ifPresent(u -> {
                u.getLessons().remove(l);
                userRepository.save(u);
            });
        });

    }

    public void updateLesson(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public byte[] getLessonImage(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folder + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }

    public void checkedImage(Lesson lesson, MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File newFile = new File(folder + File.separator + fileName);
        file.transferTo(newFile);
        lesson.setPicture(fileName);

    }

    public void save(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public Set<Lesson> findAllByUser(CurrentUser currentUser) {
        Optional<User> byId = userRepository.findById(currentUser.getUser().getId());
        return byId.map(User::getLessons)
                .orElse(Set.of());
    }

    public List<Lesson> findLast3Lessons() {
        return lessonRepository.findTop3ByOrderByIdAsc();
    }
}


