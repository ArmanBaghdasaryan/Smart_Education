package am.itspace.smart_education.common.repository;

import am.itspace.smart_education.common.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Integer> {

}
