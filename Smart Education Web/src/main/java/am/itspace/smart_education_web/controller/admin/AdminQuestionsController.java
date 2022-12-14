package am.itspace.smart_education_web.controller.admin;


import am.itspace.smart_education_common.entity.Question;
import am.itspace.smart_education_common.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/questions")
@RequiredArgsConstructor
public class AdminQuestionsController {

    private final QuestionsService questionsService;

    @GetMapping
    public String questions(ModelMap modelMap) {
        List<Question> allQuestions = questionsService.findAll();
        modelMap.addAttribute("questions", allQuestions);
        return "admin/admin_questions";
    }

    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable("id") int id) {
        questionsService.deleteById(id);
        return "redirect:/admin/questions";
    }

}
