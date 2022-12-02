package am.itspace.smart_education.common.repository;

import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface LessonRepository extends JpaRepository<Lesson,Integer> {

    Optional<Lesson> findLessonByUserSetAndId(int lessonId, int userId);
}
