package am.itspace.smart_education_common.service.impl;

import am.itspace.smart_education_common.dto.QuestionDto;
import am.itspace.smart_education_common.entity.Question;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.mapper.QuestionMapper;
import am.itspace.smart_education_common.repository.QuestionRepository;
import am.itspace.smart_education_common.repository.UserRepository;
import am.itspace.smart_education_common.security.CurrentUser;
import am.itspace.smart_education_common.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final QuestionMapper questionMapper;

    public void deleteById(int id) {
        questionRepository.deleteById(id);
    }

    public void save(Question question) {
        questionRepository.save(question);
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Optional<Question> findById(int id) {
        return questionRepository.findById(id);
    }

    public void updateQuestion(Question question) {
        questionRepository.save(question);
    }

    public Question saveQuestion(QuestionDto questionDto, @AuthenticationPrincipal CurrentUser currentUser) {
        Optional<User> byId = userRepository.findById(currentUser.getUser().getId());
        return byId.map(user -> {
                    Question question = questionMapper.map(questionDto);
                    question.setUser(user);
                    return questionRepository.save(question);
                })
                .orElse(null);

    }

    @Override
    public List<Question> searchQuestions(String keyword) {
        return questionRepository.searchQuestions(keyword);
    }

}
