package am.itspace.smart_education_common.service;
import am.itspace.smart_education_common.dto.AnswerDto;
import am.itspace.smart_education_common.entity.Answer;
import am.itspace.smart_education_common.security.CurrentUser;
import org.springframework.ui.ModelMap;
import java.util.List;
import java.util.Optional;

public interface AnswerService {

    List<Answer> findAll();

    Answer save(AnswerDto answerDto, CurrentUser currentUser);

    void deleteById(int id);

    Optional<Answer> findById(int id);

    void updateAnswer(Answer answer);

    void answerToQuestion(int id, ModelMap modelMap);

}
