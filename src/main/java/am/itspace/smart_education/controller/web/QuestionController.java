package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.entity.Question;
import am.itspace.smart_education.common.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {


    private final QuestionsService questionsService;

    public String questions(ModelMap modelMap) {
        List<Question> allQuestions = questionsService.findAll();
        modelMap.addAttribute("questions", allQuestions);
        return "web/answer";
    }

}
