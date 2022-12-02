package am.itspace.smart_education.common.service.serviceImpl;

import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.repository.LessonRepository;
import am.itspace.smart_education.common.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

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
