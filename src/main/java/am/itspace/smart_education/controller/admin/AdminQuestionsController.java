package am.itspace.smart_education.controller.admin;


import am.itspace.smart_education.common.entity.Question;
import am.itspace.smart_education.common.service.QuestionsService;
import am.itspace.smart_education.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/update/{id}")
    public String updateQuestion(ModelMap modelMap,
                                 @PathVariable("id") int id) {
        Optional<Question> byId = questionsService.findById(id);
        if (byId.isEmpty()) {
            return "redirect:/admin/questions";
        }
        modelMap.addAttribute("questions", byId.get());
        return "admin/editQuestion";
    }


    @PostMapping("/update")
    public String updateQuestion(@ModelAttribute Question question) {
        questionsService.updateQuestion(question);
        return "redirect:/admin/questions";
    }
}
