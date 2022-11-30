package am.itspace.smart_education.common.service;

import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    @Value("${smart.education.images.folder}")
    private String folder;
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public void save(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public void deleteById(int id) {
        lessonRepository.deleteById(id);
    }

    public Optional<Lesson> findById(int id) {
        return lessonRepository.findById(id);
    }

    public void updateLesson(Lesson lesson) {
        lessonRepository.save(lesson);
    }
    public byte[] getUserImage(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folder + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }
}
