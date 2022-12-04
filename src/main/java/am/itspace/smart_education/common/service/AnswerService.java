package am.itspace.smart_education.common.service;


import am.itspace.smart_education.common.entity.Answer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AnswerService {


    List<Answer> findAll();

    void save(Answer answers);

    void deleteById(int id);

    Optional<Answer> findById(int id);

    void updateAnswer(Answer answer);
}
