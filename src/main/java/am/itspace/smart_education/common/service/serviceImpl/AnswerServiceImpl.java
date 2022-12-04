package am.itspace.smart_education.common.service.serviceImpl;


import am.itspace.smart_education.common.entity.Answer;
import am.itspace.smart_education.common.entity.Question;
import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.repository.AnswerRepository;
import am.itspace.smart_education.common.repository.QuestionRepository;
import am.itspace.smart_education.common.repository.UserRepository;
import am.itspace.smart_education.common.service.AnswerService;
import am.itspace.smart_education.dto.AnswerDto;
import am.itspace.smart_education.mapper.AnswerMapper;
import am.itspace.smart_education.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnswerServiceImpl implements AnswerService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    public List<Answer> findAll() {
        return answerRepository.findAll();
    }


    public void save(AnswerDto answerDto,CurrentUser currentUser) {
        Optional<User> byId = userRepository.findById(currentUser.getUser().getId());
        Optional<Question> questionById = questionRepository.findById(answerDto.getQuestionId());
        byId.map(user -> {
            log.info("User with id was found: {}", user.getId());
            Answer answer = answerMapper.map(answerDto);
            answer.setUser(user);
            log.info("Answer with id was found: {}", answer.getId());
            questionById.ifPresent(answer::setQuestion);
            return answerRepository.save(answer);
        });
    }

    public void deleteById(int id) {
        answerRepository.deleteById(id);
    }

    public Optional<Answer> findById(int id) {
        return answerRepository.findById(id);
    }

    public void updateAnswer(Answer answer) {
        answerRepository.save(answer);
    }
}
