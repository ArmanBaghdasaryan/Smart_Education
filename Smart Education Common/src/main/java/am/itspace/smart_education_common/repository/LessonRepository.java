package am.itspace.smart_education_common.repository;

import am.itspace.smart_education_common.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {


    List<Lesson> findTop3ByOrderByIdAsc();
}
