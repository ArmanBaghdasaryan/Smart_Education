package am.itspace.smart_education_web.service;

import am.itspace.smart_education_common.entity.Lesson;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LessonServiceW2 {
    void save(Lesson lesson, MultipartFile multipartFile) throws IOException;
    void checkedImage(Lesson lesson, MultipartFile file) throws IOException;
}
