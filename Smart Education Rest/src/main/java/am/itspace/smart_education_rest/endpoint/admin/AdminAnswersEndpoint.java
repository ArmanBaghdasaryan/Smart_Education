package am.itspace.smart_education_rest.endpoint.admin;

import am.itspace.smart_education_common.dto.AnswerDto;
import am.itspace.smart_education_common.entity.Answer;
import am.itspace.smart_education_common.mapper.AnswerMapper;
import am.itspace.smart_education_common.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/answers")
public class AdminAnswersEndpoint {

    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    @GetMapping
    public ResponseEntity<List<Answer>> getAllAnswer() {
        return ResponseEntity.ok(answerService.findAll());
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Answer> getAnswerId(@PathVariable("id") int id) {
        return answerService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Answer> createAnswer(@RequestBody AnswerDto answerDto) {
        answerService.updateAnswer(answerMapper.map(answerDto));
        log.info("Answer successful created {} ", answerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("id") int id) {
        answerService.deleteById(id);
        log.info("Answer with "+ id + "id was deleted");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Answer> updateAnswerById(@PathVariable("id") int id, @RequestBody Answer answer) {
        Optional<Answer> byId = answerService.findById(id);
        if (byId.isEmpty()) {
            log.error("Answer with " + id + "id wasn't found");
            return ResponseEntity.badRequest().build();
        }
        answer.setId(id);
        answerService.updateAnswer(answer);
        log.info("Answer with id successfully updated: {}", id);
        return ResponseEntity.ok(answer);
    }


}
