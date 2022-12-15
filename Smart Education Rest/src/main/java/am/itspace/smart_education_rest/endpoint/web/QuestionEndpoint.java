package am.itspace.smart_education_rest.endpoint.web;

import am.itspace.smart_education_common.dto.QuestionDto;
import am.itspace.smart_education_common.entity.Question;
import am.itspace.smart_education_common.service.QuestionsService;
import am.itspace.smart_education_rest.security.CurrentUser;
import am.itspace.smart_education_rest.service.QuestionServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionEndpoint {

    private final QuestionServiceV2 questionsServiceV2;
    private final QuestionsService questionsService;

    @GetMapping
    public ResponseEntity<List<Question>> questions() {
        List<Question> questions = questionsService.findAll();
        return ResponseEntity.ok(questions);
    }

    @PostMapping
    public ResponseEntity<Question> saveQuestion(@RequestBody QuestionDto questionDto,
                                                 @AuthenticationPrincipal CurrentUser currentUser) {
        Question question = questionsServiceV2.saveQuestion(questionDto, currentUser);
        return ResponseEntity.ok(question);

    }

    @GetMapping("/search")
    public ResponseEntity<List<Question>> searchQuestion(@RequestParam String keyword) {
        List<Question> searchQuestionsByKeyword = questionsService.searchQuestions(keyword);
        return ResponseEntity.ok(searchQuestionsByKeyword);
    }

}
