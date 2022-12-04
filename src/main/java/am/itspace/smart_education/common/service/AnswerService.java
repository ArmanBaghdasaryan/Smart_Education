package am.itspace.smart_education.common.service;


import am.itspace.smart_education.common.entity.Answer;
import am.itspace.smart_education.dto.AnswerDto;
import am.itspace.smart_education.security.CurrentUser;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;

@Service
public interface AnswerService {


    List<Answer> findAll();

    void save(AnswerDto answerDto, CurrentUser currentUser);

    void deleteById(int id);

    Optional<Answer> findById(int id);

    void updateAnswer(Answer answer);

    void answerToQuestion(int id, ModelMap modelMap);
}
