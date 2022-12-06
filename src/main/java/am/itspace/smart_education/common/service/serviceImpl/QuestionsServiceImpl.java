package am.itspace.smart_education.common.service.serviceImpl;

import am.itspace.smart_education.common.entity.Question;
import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.repository.QuestionRepository;
import am.itspace.smart_education.common.repository.UserRepository;
import am.itspace.smart_education.common.service.QuestionsService;
import am.itspace.smart_education.dto.QuestionDto;
import am.itspace.smart_education.mapper.QuestionMapper;
import am.itspace.smart_education.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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

    public void saveQuestion(QuestionDto questionDto, @AuthenticationPrincipal CurrentUser currentUser) {
        Optional<User> byId = userRepository.findById(currentUser.getUser().getId());
        byId.map(user -> {
            Question question = questionMapper.map(questionDto);
            question.setUser(user);
            return questionRepository.save(question);
        });

    }

}
