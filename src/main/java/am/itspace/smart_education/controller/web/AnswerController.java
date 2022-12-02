package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.entity.Answer;
import am.itspace.smart_education.common.entity.Question;
import am.itspace.smart_education.common.repository.AnswerRepository;
import am.itspace.smart_education.common.repository.QuestionRepository;
import am.itspace.smart_education.dto.AnswerDto;
import am.itspace.smart_education.mapper.AnswerMapper;
import am.itspace.smart_education.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerRepository answerRepository;
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
        return ResponseEntity.ok(answerRepository.save(answerMapper.map(answerDto)));

    }
}
