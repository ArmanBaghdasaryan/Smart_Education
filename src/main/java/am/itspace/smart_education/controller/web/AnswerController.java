package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.entity.Answer;
import am.itspace.smart_education.common.service.AnswerService;
import am.itspace.smart_education.dto.AnswerDto;
import am.itspace.smart_education.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/single_question")
    @ResponseBody
    public AnswerDto answerSave(@RequestBody AnswerDto answerDto,
                                             @AuthenticationPrincipal CurrentUser currentUser) {
        Answer answer = answerService.save(answerDto, currentUser);
        answerDto.setQUsername(answer.getQuestion().getUser().getName());
        answerDto.setAUsername(answer.getUser().getName());
        answerDto.setDescription(answer.getQuestion().getDescription());
        return answerDto;
    }

    @GetMapping("/single_question/{id}")
    public String answerToQuestion(@PathVariable("id") int id, ModelMap modelMap) {
        answerService.answerToQuestion(id, modelMap);
        return "web/single_question";
    }
}