package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.entity.Question;
import am.itspace.smart_education.common.service.QuestionsService;
import am.itspace.smart_education.dto.QuestionDto;
import am.itspace.smart_education.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class QuestionController {


    private final QuestionsService questionsService;

    public String questions(ModelMap modelMap) {
        List<Question> allQuestions = questionsService.findAll();
        modelMap.addAttribute("questions", allQuestions);
        return "web/answer";

    @GetMapping("/question")
    public String questions(ModelMap modelMap) {
        List<Question> questions = questionsService.findAll();
        modelMap.addAttribute("questions", questions);
        return "web/question";
    }

    @PostMapping("/question")
    @ResponseBody
    public ResponseEntity<Question> saveQuestion(@RequestBody QuestionDto questionDto,
                                                 @AuthenticationPrincipal CurrentUser currentUser) {
        questionsService.saveQuestion(questionDto, currentUser);
        return ResponseEntity.ok().build();

    }


}
