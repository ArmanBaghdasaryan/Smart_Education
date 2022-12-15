package am.itspace.smart_education_rest.endpoint.admin;

import am.itspace.smart_education_common.dto.QuestionDto;
import am.itspace.smart_education_common.entity.Question;
import am.itspace.smart_education_common.mapper.QuestionMapper;
import am.itspace.smart_education_common.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/questions")
@Slf4j
public class AdminQuestionsEndpoint {
    private final QuestionsService questionsService;
    private final QuestionMapper questionMapper;

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionsService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<Question> getQuestionId(@PathVariable("id") int id) {
        return questionsService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionDto questionDto) {
        questionsService.updateQuestion(questionMapper.map(questionDto));
        log.info("Question successful created {} ", questionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable("id") int id) {
        questionsService.deleteById(id);
        log.info("Question with " + id + "id was deleted");
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Question> updateQuestionById(@PathVariable("id") int id,
                                                       @RequestBody Question question) {
        Optional<Question> byId = questionsService.findById(id);
        if (byId.isEmpty()) {
            log.error("Question with " + id + "id wasn't found");
            return ResponseEntity.badRequest().build();
        }
        question.setId(id);
        questionsService.updateQuestion(question);
        log.info("Question with id successfully updated: {}", id);
        return ResponseEntity.ok(question);
    }
}
