package am.itspace.smart_education_rest.service;

import am.itspace.smart_education_common.entity.Lesson;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LessonServiceW2 {

    void checkedImage(Lesson lesson, MultipartFile file) throws IOException;
}
