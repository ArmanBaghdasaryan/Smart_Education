package am.itspace.smart_education.common.services;

import am.itspace.smart_education.common.entity.Questions;
import am.itspace.smart_education.common.repositories.QuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionsService {

    private final QuestionsRepository questionsRepository;


    public List<Questions> findAll() {
        return questionsRepository.findAll();
    }

    public void save(Questions questions) {
        questionsRepository.save(questions);
    }

    public void deleteById(int id) {
        questionsRepository.deleteById(id);
    }

}
