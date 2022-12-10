package am.itspace.smart_education_common.service;

import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.security.CurrentUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    Set<Lesson> findAllByUser(CurrentUser currentUser);

    void deleteSubscribe(int lessonId, int userId);

    List<Lesson> findLast3Lessons();
}
