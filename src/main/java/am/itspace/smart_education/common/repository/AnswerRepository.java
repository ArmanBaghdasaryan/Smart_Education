package am.itspace.smart_education.common.repository;

import am.itspace.smart_education.common.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {


    List<Answer> findAnswersByQuestionId(int questionId);


}
