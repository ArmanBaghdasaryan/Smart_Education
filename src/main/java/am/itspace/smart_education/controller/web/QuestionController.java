package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.entity.Question;
import am.itspace.smart_education.common.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionRepository questionRepository;

//    @GetMapping("/answer_save")
    public String questions(ModelMap modelMap) {
        List<Question> allQuestions = questionRepository.findAll();
        modelMap.addAttribute("questions", allQuestions);
        return "web/answer";
    }

}
