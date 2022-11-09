package am.itspace.smart_education.common.service;

import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

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
}
