package am.itspace.smart_education.controller.admin;


import am.itspace.smart_education.common.entity.Question;
import am.itspace.smart_education.common.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminQuestionsController {

    private final QuestionsService questionsService;

    @GetMapping("/admin/questions")
    public String questions(ModelMap modelMap) {
        List<Question> allQuestions = questionsService.findAll();
        modelMap.addAttribute("questions", allQuestions);
        return "questions";
    }

    @GetMapping("/admin/questions/add")
    public String addQuestions() {
        return "addQuestions";
    }


    @PostMapping("/admin/questions/add")
    public String addQuestions(@ModelAttribute Question question) {
        questionsService.save(question);
        return "redirect:/admin/questions";
    }


    @GetMapping("/admin/questions/delete")
    public String deleteQuestions(@RequestParam("id") int id) {
        questionsService.deleteById(id);
        return "redirect:/admin/questions";
    }

    @GetMapping("/admin/questions/update")
    public String update(@ModelAttribute Question question) {
        questionsService.save(question);
        return "redirect:/admin/questions";
    }


}
