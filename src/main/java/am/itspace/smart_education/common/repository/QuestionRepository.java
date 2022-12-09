package am.itspace.smart_education.common.repository;

import am.itspace.smart_education.common.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query(value = "select * from questions s where s.description like %:keyword%", nativeQuery = true)
    List<Question> searchQuestions(@Param(value = "keyword") String keyword);
}
