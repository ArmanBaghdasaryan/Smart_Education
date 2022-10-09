package am.itspace.smart_education.common.service;


import am.itspace.smart_education.common.entity.Answer;
import am.itspace.smart_education.common.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {

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

}
