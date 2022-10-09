package am.itspace.smart_education.controller.admin;

import am.itspace.smart_education.common.entity.Answer;
import am.itspace.smart_education.common.service.AnswerService;
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
public class AdminAnswersController {

    private final AnswerService answerService;

    @GetMapping("/admin/answers")
    public String answers(ModelMap modelMap) {
        List<Answer> allAnswers = answerService.findAll();
        modelMap.addAttribute("answers", allAnswers);
        return "answers";
    }

    @GetMapping("/admin/answers/add")
    public String addAnswers() {
        return "addAnswers";
    }


    @PostMapping("/admin/answers/add")
    public String addAnswers(@ModelAttribute Answer answer) {
        answerService.save(answer);
        return "redirect:/admin/answers";
    }


    @GetMapping("/admin/answers/delete")
    public String deleteAnswers(@RequestParam("id") int id) {
        answerService.deleteById(id);
        return "redirect:/admin/answers";
    }

    @GetMapping("/admin/answers/update")
    public String update(@ModelAttribute Answer answer) {
        answerService.save(answer);
        return "redirect:/admin/answers";
    }

}
