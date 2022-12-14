package am.itspace.smart_education_rest.endpoint.web;

import am.itspace.smart_education_common.dto.AnswerRequestDto;
import am.itspace.smart_education_common.entity.Answer;
import am.itspace.smart_education_rest.security.CurrentUser;
import am.itspace.smart_education_common.service.AnswerService;
import am.itspace.smart_education_rest.service.impl.AnswerServiceImplV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/answers")
public class AnswerEndpoint {


    private final AnswerServiceImplV2 answerServiceV2;

    @PostMapping
    public ResponseEntity<AnswerRequestDto> answerSave(@RequestBody AnswerRequestDto answerDto,
                                                       @AuthenticationPrincipal CurrentUser currentUser) {
        Answer answer = answerServiceV2.save(answerDto, currentUser);
        return ResponseEntity.ok(AnswerRequestDto.builder()
                .answerText(answer.getAnswerText())
                .questionId(answer.getQuestion().getId())
                .build());
    }

}
