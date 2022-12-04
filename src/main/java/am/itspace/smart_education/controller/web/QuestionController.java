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
public class QuestionController {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @GetMapping("/question")
    public String questions(ModelMap modelMap) {
        List<Question> questions = questionRepository.findAll();
        modelMap.addAttribute("questions", questions);
        return "web/question";
    }

    @PostMapping("/question")
    @ResponseBody
    public ResponseEntity<Question> saveQuestion(@RequestBody Question question,
                                                 @AuthenticationPrincipal CurrentUser currentUser) {
        Optional<User> byId = userRepository.findById(currentUser.getUser().getId());
        byId.map(user -> {
            question.setUser(user);
            return ResponseEntity.ok(questionRepository.save(question));
        });
        return ResponseEntity.ok().build();
    }


}
