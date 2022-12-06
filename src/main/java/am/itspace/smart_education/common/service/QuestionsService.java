package am.itspace.smart_education.common.service;

import am.itspace.smart_education.common.entity.Question;
import am.itspace.smart_education.dto.QuestionDto;
import am.itspace.smart_education.security.CurrentUser;

import java.util.List;
import java.util.Optional;


public interface QuestionsService {

    void deleteById(int id);

    List<Question> findAll();

    Optional<Question> findById(int id);

    void updateQuestion(Question question);

    void saveQuestion(QuestionDto questionDto, CurrentUser currentUser);
}
