package am.itspace.smart_education.common.service.serviceImpl;


import am.itspace.smart_education.common.entity.Answer;
import am.itspace.smart_education.common.repository.AnswerRepository;
import am.itspace.smart_education.common.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    public void save(Answer answers) {
        answerRepository.save(answers);
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
