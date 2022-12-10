package am.itspace.smart_education_common.service;

import am.itspace.smart_education_common.dto.QuestionDto;
import am.itspace.smart_education_common.entity.Question;
import am.itspace.smart_education_common.security.CurrentUser;

import java.util.List;
import java.util.Optional;


public interface QuestionsService {

    void deleteById(int id);

    List<Question> findAll();

    Optional<Question> findById(int id);

    void updateQuestion(Question question);

    Question saveQuestion(QuestionDto questionDto, CurrentUser currentUser);

    List<Question> searchQuestions(String keyword);
}
