package am.itspace.smart_education.common.service;

import am.itspace.smart_education.common.entity.Lesson;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LessonService {


    List<Lesson> findAll();

    void save(Lesson lesson);

    void deleteById(int id);

    Optional<Lesson> findById(int id);

    void updateLesson(Lesson lesson);
}
