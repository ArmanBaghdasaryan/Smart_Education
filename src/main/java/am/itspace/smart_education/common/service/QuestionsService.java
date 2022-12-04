package am.itspace.smart_education.common.service;

import am.itspace.smart_education.common.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface QuestionsService  {

    void deleteById(int id);

    void save(Question question);

    List<Question> findAll();

    Optional<Question> findById(int id);

    void updateQuestion(Question question);

}
