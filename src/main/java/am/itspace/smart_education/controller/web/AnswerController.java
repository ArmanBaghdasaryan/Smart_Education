package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.entity.Answer;
import am.itspace.smart_education.common.entity.Question;
import am.itspace.smart_education.common.service.AnswerService;
import am.itspace.smart_education.common.service.QuestionsService;
import am.itspace.smart_education.dto.AnswerDto;
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

    private final AnswerService answerService;
    private final QuestionsService questionsService;

    @GetMapping("/answer_save")
    public String questions(ModelMap modelMap) {
        List<Question> questions = questionsService.findAll();
        modelMap.addAttribute("questions", questions);
        List<Answer> answers = answerService.findAll();
        modelMap.addAttribute("answers", answers);
        return "web/answer";
    }

    @PostMapping("/answer_save")
    @ResponseBody
    public ResponseEntity<Answer> chatSave(@RequestBody AnswerDto answerDto,
                                           @AuthenticationPrincipal CurrentUser currentUser) {
        answerService.save(answerDto, currentUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/single_question")
    public String singleQuestion(@RequestParam int questionId, ModelMap modelMap) {
        Optional<Question> byId = questionsService.findById(questionId);
        log.info("Question with id was found: {}", byId.get().getId());
        byId.ifPresent(question -> {
            modelMap.addAttribute("question", question);
        });

        return "web/single_question";
    }

}
