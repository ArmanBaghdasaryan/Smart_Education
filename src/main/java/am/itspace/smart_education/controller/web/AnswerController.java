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

    @PostMapping("/single_question")
    @ResponseBody
    public ResponseEntity<Answer> answerSave(@RequestBody AnswerDto answerDto,
                                             @AuthenticationPrincipal CurrentUser currentUser) {
        Optional<User> byId = userRepository.findById(currentUser.getUser().getId());
        Optional<Question> questionById = questionRepository.findById(answerDto.getQuestionId());
        byId.map(user -> {
            log.info("Answer with id was found: {}", user.getId());
            Answer answer = answerMapper.map(answerDto);
            answer.setUser(user);
            log.info("Question with id was found: {}", answer.getId());
            questionById.ifPresent(answer::setQuestion);
            return ResponseEntity.ok(answerRepository.save(answer));
        });
        return ResponseEntity.ok().build();
    }

    @GetMapping("/single_question/{id}")
    public String answerToQuestion(@PathVariable("id") int id, ModelMap modelMap) {
        List<Answer> answers = answerRepository.findAnswersByQuestionId(id);
        modelMap.addAttribute("answers", answers);
        Optional<Question> byId = questionRepository.findById(id);
        log.info("Question with id was found: {}", byId.get().getId());
        byId.ifPresent(question -> {
            modelMap.addAttribute("question", question);
        });

        return "web/single_question";
    }
}