package am.itspace.smart_education_rest.service.impl;

import am.itspace.smart_education_common.dto.AnswerRequestDto;
import am.itspace.smart_education_common.entity.Answer;
import am.itspace.smart_education_common.entity.Question;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.mapper.AnswerMapper;
import am.itspace.smart_education_common.repository.AnswerRepository;
import am.itspace.smart_education_common.repository.QuestionRepository;
import am.itspace.smart_education_common.repository.UserRepository;
import am.itspace.smart_education_rest.security.CurrentUser;
import am.itspace.smart_education_rest.service.AnswerServiceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnswerServiceImplV2 implements AnswerServiceV2 {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AnswerMapper answerMapper;
    private final AnswerRepository answerRepository;

    public Answer save(AnswerRequestDto answerDto, @AuthenticationPrincipal CurrentUser currentUser) {
        Optional<User> userById = userRepository.findById(currentUser.getUser().getId());
        Optional<Question> questionById = questionRepository.findById(answerDto.getQuestionId());
        return userById.map(user -> {
                    log.info("User with id was found: {}", user.getId());
                    Answer answer = answerMapper.mapTo(answerDto);
                    answer.setUser(user);
                    questionById.ifPresent(question -> {
                        log.info("Question with id was found: {}", question.getId());
                        answer.setQuestion(question);
                    });
                    return answerRepository.save(answer);
                })
                .orElse(null);
    }

}
