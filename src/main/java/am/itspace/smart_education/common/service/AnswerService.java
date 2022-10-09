package am.itspace.smart_education.common.services;

import am.itspace.smart_education.common.entity.Answers;
import am.itspace.smart_education.common.repositories.AnswersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswersRepository answersRepository;

    public List<Answers> findAll() {
        return answersRepository.findAll();
    }

    public void save(Answers answers) {
        answersRepository.save(answers);
    }

    public void deleteById(int id) {
        answersRepository.deleteById(id);
    }

}
