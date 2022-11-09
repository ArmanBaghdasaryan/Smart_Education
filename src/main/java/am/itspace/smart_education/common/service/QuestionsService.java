package am.itspace.smart_education.common.service;

import am.itspace.smart_education.common.entity.Question;
import am.itspace.smart_education.common.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionsService {

    private final QuestionRepository questionRepository;

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
}
