package am.itspace.smart_education.common.repositories;

import am.itspace.smart_education.common.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson,Integer> {

}
