package am.itspace.smart_education_rest.service.serviceImpl;

import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.repository.LessonRepository;

import am.itspace.smart_education_rest.service.LessonServiceW2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class LessonServiceImplW2 implements LessonServiceW2 {
    private final LessonRepository lessonRepository;
    @Value("${smart.education.images.folder}")
    private String folder;

    public void save(Lesson lesson, MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty() && multipartFile.getSize() > 0) {
            checkedImage(lesson, multipartFile);
        }
        lessonRepository.save(lesson);
    }

    public void checkedImage(Lesson lesson, MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File newFile = new File(folder + File.separator + fileName);
        file.transferTo(newFile);
        lesson.setPicture(fileName);

    }
}
