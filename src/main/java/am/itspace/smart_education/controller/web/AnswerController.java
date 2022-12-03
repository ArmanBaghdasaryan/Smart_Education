package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.entity.Answer;
import am.itspace.smart_education.common.entity.Question;
import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.repository.AnswerRepository;
import am.itspace.smart_education.common.repository.QuestionRepository;
import am.itspace.smart_education.common.repository.UserRepository;
import am.itspace.smart_education.dto.AnswerDto;
import am.itspace.smart_education.mapper.AnswerMapper;
import am.itspace.smart_education.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AnswerController {

    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AnswerMapper answerMapper;

    @GetMapping("/answer_save")
    public String questions(ModelMap modelMap) {
        List<Question> questions = questionRepository.findAll();
        modelMap.addAttribute("questions", questions);
        List<Answer> answers = answerRepository.findAll();
        modelMap.addAttribute("answers", answers);
        return "web/answer";
    }

    @PostMapping("/answer_save")
    @ResponseBody
    public ResponseEntity<Answer> chatSave(@RequestBody AnswerDto answerDto,
                                           @AuthenticationPrincipal CurrentUser currentUser) {
        Optional<User> byId = userRepository.findById(currentUser.getUser().getId());
        Optional<Question> questionById = questionRepository.findById(answerDto.getQuestionId());
        byId.map(user -> {
            log.info("User with id was found: {}", user.getId());
            Answer answer = answerMapper.map(answerDto);
            answer.setUser(user);
            log.info("Answer with id was found: {}", answer.getId());
            questionById.ifPresent(answer::setQuestion);
            return ResponseEntity.ok(answerRepository.save(answer));
        });
        return ResponseEntity.ok().build();
    }

    @GetMapping("/single_question")
    public String singleQuestion(@RequestParam int questionId, ModelMap modelMap) {
        Optional<Question> byId = questionRepository.findById(questionId);
        log.info("Question with id was found: {}", byId.get().getId());
        byId.ifPresent(question -> {
            modelMap.addAttribute("question", question);
        });

        return "web/single_question";
    }

}
