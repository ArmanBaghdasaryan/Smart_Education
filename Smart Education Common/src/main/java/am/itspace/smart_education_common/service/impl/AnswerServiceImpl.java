package am.itspace.smart_education_common.service.impl;


import am.itspace.smart_education_common.dto.AnswerDto;
import am.itspace.smart_education_common.entity.Answer;
import am.itspace.smart_education_common.entity.Question;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.mapper.AnswerMapper;
import am.itspace.smart_education_common.repository.AnswerRepository;
import am.itspace.smart_education_common.repository.QuestionRepository;
import am.itspace.smart_education_common.repository.UserRepository;
import am.itspace.smart_education_common.security.CurrentUser;
import am.itspace.smart_education_common.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AnswerMapper answerMapper;

    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    public Answer save(AnswerDto answerDto, @AuthenticationPrincipal CurrentUser currentUser) {
        Optional<User> byId = userRepository.findById(currentUser.getUser().getId());
        Optional<Question> questionById = questionRepository.findById(answerDto.getQuestionId());
        return byId.map(user -> {
                    log.info("User with id was found: {}", user.getId());
                    Answer answer = answerMapper.map(answerDto);
                    answer.setUser(user);
                    questionById.ifPresent(question -> {
                        log.info("Question with id was found: {}", question.getId());
                        answer.setQuestion(question);
                    });
                    return answerRepository.save(answer);
                })
                .orElse(null);
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

    public void answerToQuestion(int id, ModelMap modelMap) {
        List<Answer> answers = answerRepository.findAnswersByQuestionId(id);
        modelMap.addAttribute("answers", answers);
        Optional<Question> byId = questionRepository.findById(id);
        byId.ifPresent(question -> {
            log.info("Question with id was found: {}", byId.get().getId());
            modelMap.addAttribute("question", question);
        });
    }

}