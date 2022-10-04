package am.itspace.smart_education.common.repositories;

import am.itspace.smart_education.common.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Questions,Integer> {

}
