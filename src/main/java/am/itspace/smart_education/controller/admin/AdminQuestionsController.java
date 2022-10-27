package am.itspace.smart_education.controller.admin;


import am.itspace.smart_education.common.entity.Question;
import am.itspace.smart_education.common.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminQuestionsController {

    private final QuestionsService questionsService;

    @GetMapping("/questions")
    public String questions(ModelMap modelMap) {
        List<Question> allQuestions = questionsService.findAll();
        modelMap.addAttribute("questions", allQuestions);
        return "questions";
    }

    @GetMapping("/questions/add")
    public String addQuestions() {
        return "addQuestions";
    }


    @PostMapping("/questions/add")
    public String addQuestions(@ModelAttribute Question question) {
        questionsService.save(question);
        return "redirect:/admin/questions";
    }


    @GetMapping("/questions/delete")
    public String deleteQuestions(@RequestParam("id") int id) {
        questionsService.deleteById(id);
        return "redirect:/admin/questions";
    }

    @GetMapping("/questions/update")
    public String update(@ModelAttribute Question question) {
        questionsService.save(question);
        return "redirect:/admin/questions";
    }


}
