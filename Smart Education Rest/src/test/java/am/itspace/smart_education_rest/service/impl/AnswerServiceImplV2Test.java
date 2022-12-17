package am.itspace.smart_education_rest.service.impl;

import am.itspace.smart_education_common.dto.AnswerRequestDto;
import am.itspace.smart_education_common.entity.Answer;
import am.itspace.smart_education_common.entity.Role;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.repository.AnswerRepository;
import am.itspace.smart_education_common.service.impl.AnswerServiceImpl;
import am.itspace.smart_education_rest.exception.AuthenticationException;
import am.itspace.smart_education_rest.exception.EntityNotFoundException;
import am.itspace.smart_education_rest.security.CurrentUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AnswerServiceImplV2Test {
    @Autowired
    private AnswerServiceImplV2 answerServiceImplV2;
    @Autowired
    private AnswerServiceImpl answerService;
    @MockBean
    private AnswerRepository answerRepository;


    @Test
    void testSave() throws AuthenticationException, EntityNotFoundException {
        AnswerRequestDto answerDto = new AnswerRequestDto("Answer Text", 1);
        answerServiceImplV2.save(answerDto, new CurrentUser(new User(1, "Name", "surname", "arman@mail.ru", "123", "teacher", "Picture",
                "0982011", Role.USER, new HashSet<>(), "123", true)));
    }

    @Test
    void testSave2() throws AuthenticationException, EntityNotFoundException {
        AnswerRequestDto answerDto = new AnswerRequestDto("Answer Text", 1);
        assertEquals("Answer Text",
                answerServiceImplV2.save(answerDto, new CurrentUser(new User(1, "Name", "surname", "arman@mail.ru", "123", "teacher", "Picture",
                        "0982011", Role.USER, new HashSet<>(), "123", true))).getAnswerText());
    }

    @Test
    void testFindAll() {
        ArrayList<Answer> expectedList = new ArrayList<>();
        when(answerRepository.findAll()).thenReturn(expectedList);
        List<Answer> actualList = answerService.findAll();
        assertEquals(expectedList, actualList);
        verify(answerRepository).findAll();
    }

    @Test
    void deleteById() {
        Optional<Answer> ofResult = Optional.of(new Answer());
        doNothing().when(answerRepository).deleteById(Mockito.any());
        when(answerRepository.findById(Mockito.any())).thenReturn(ofResult);
        answerService.deleteById(1);
        verify(answerRepository).deleteById(Mockito.any());
    }


}

