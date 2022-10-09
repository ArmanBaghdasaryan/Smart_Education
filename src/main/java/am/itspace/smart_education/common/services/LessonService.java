package am.itspace.smart_education.common.services;

import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.repositories.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
