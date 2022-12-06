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
    public ResponseEntity<Answer> answerSave(@RequestBody AnswerDto answerDto,
                                             @AuthenticationPrincipal CurrentUser currentUser) {
        answerService.save(answerDto, currentUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/single_question/{id}")
    public String answerToQuestion(@PathVariable("id") int id, ModelMap modelMap) {
        answerService.answerToQuestion(id, modelMap);
        return "web/single_question";
    }
}