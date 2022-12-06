package am.itspace.smart_education.controller.admin;

import am.itspace.smart_education.common.entity.Answer;
import am.itspace.smart_education.common.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/answers")
@RequiredArgsConstructor
public class AdminAnswersController {

    private final AnswerService answerService;

    @GetMapping
    public String answer(ModelMap modelMap) {
        List<Answer> allAnswers = answerService.findAll();
        modelMap.addAttribute("answers", allAnswers);
        return "admin/admin_answers";
    }

    @GetMapping("/delete/{id}")
    public String deleteAnswer(@PathVariable("id") int id) {
        answerService.deleteById(id);
        return "redirect:/admin/answers";
    }

    @GetMapping("/update/{id}")
    public String updateAnswer(ModelMap modelMap,
                               @PathVariable("id") int id) {
        Optional<Answer> byId = answerService.findById(id);
        byId.ifPresent(answer -> modelMap.addAttribute("answers", answer));
        return "admin/editAnswer";
    }

    @PostMapping("/update")
    public String updateAnswer(@ModelAttribute Answer answer) {
        answerService.updateAnswer(answer);
        return "redirect:/admin/answers";
    }
}


