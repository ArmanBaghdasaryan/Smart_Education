package am.itspace.smart_education.controller.admin;

import am.itspace.smart_education.common.entity.Answer;
import am.itspace.smart_education.common.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminAnswersController {

    private final AnswerService answerService;

    @GetMapping("/answers")
    public String answers(ModelMap modelMap) {
        List<Answer> allAnswers = answerService.findAll();
        modelMap.addAttribute("answers", allAnswers);
        return "admin/admin_answers";
    }

    @GetMapping("/answers/add")
    public String addAnswers() {
        return "addAnswers";
    }


    @PostMapping("/answers/add")
    public String addAnswers(@ModelAttribute Answer answer) {
        answerService.save(answer);
        return "redirect:admin/answers";
    }


    @GetMapping("/answers/delete")
    public String deleteAnswers(@RequestParam("id") int id) {
        answerService.deleteById(id);
        return "redirect:admin/answers";
    }

    @GetMapping("/answers/update")
    public String update(@ModelAttribute Answer answer) {
        answerService.save(answer);
        return "redirect:admin/answers";
    }

}
