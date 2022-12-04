package am.itspace.smart_education.common.service;

import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface LessonService {


    Optional<Lesson> findById(int id);

    List<Lesson> findAll();

    void save(Lesson lesson, MultipartFile multipartFile) throws IOException;


    void deleteById(int id);

    void subscribe(int lessonId, int userId);

    void updateLesson(Lesson lesson);

    byte[] getLessonImage(String fileName) throws IOException;

    void checkedImage(Lesson lesson, MultipartFile file) throws IOException;

    void save(Lesson lesson);
//    List<Lesson> findAllByUserSet(Set<User> userSet);

}
