package am.itspace.smart_education_rest.endpoint.admin;

import am.itspace.smart_education_common.dto.QuestionDto;
import am.itspace.smart_education_common.entity.Question;
import am.itspace.smart_education_common.mapper.QuestionMapper;
import am.itspace.smart_education_common.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/questions")
public class AdminQuestionsEndpoint {
    private final QuestionsService questionsService;
    private final QuestionMapper questionMapper;

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionId(@PathVariable("id") int id) {
        return questionsService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionDto questionDto) {
        questionsService.updateQuestion(questionMapper.map(questionDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable("id") int id) {
        questionsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Question> updateQuestionById(@PathVariable("id") int id,
                                                       @RequestBody Question question) {
        Optional<Question> byId = questionsService.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        question.setId(id);
        questionsService.updateQuestion(question);
        return ResponseEntity.ok(question);
    }
}
