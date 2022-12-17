package am.itspace.smart_education_rest.service.impl;

import am.itspace.smart_education_common.entity.Question;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.repository.QuestionRepository;
import am.itspace.smart_education_common.service.QuestionsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class QuestionServiceTest {

    @MockBean
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionsService questionsService;

    @Test
    void updateQuestion() {
        when(questionRepository.save(any())).thenReturn(buildQuestion());
        Question question = buildQuestion();
        question.setId(1);
        questionsService.updateQuestion(question);
        verify(questionRepository, times(1)).save(any());
    }

    @Test
    void findAll() {
        Question question = buildQuestion();
        List<Question> questionList = List.of(question);
        when(questionRepository.findAll()).thenReturn(questionList);
        List<Question> all = questionsService.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    void findById() {
        Question question = buildQuestion();
        when(questionRepository.findById(any())).thenReturn(Optional.of(question));
        Optional<Question> byId = questionsService.findById(question.getId());
        verify(questionRepository).findById(any());
        assertTrue(byId.isPresent());
        assertEquals(byId.get().getId(), question.getId());
    }

    @Test
    void delete() {
        when(questionRepository.findById(buildQuestion().getId())).thenReturn(Optional.of(buildQuestion()));
        questionsService.deleteById(buildQuestion().getId());
        verify(questionRepository).deleteById(buildQuestion().getId());
    }


    private static Question buildQuestion() {
        return Question.builder()
                .id(1)
                .description("question text")
                .user(User.builder()
                        .name("poxos")
                        .surname("poxosyan")
                        .build())
                .build();
    }
}
