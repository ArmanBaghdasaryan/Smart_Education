package am.itspace.smart_education_rest.service.impl;

import am.itspace.smart_education_common.entity.*;
import am.itspace.smart_education_common.repository.UserRepository;
import am.itspace.smart_education_rest.service.UserServiceV2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceImplW2Test {

    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserServiceV2 userServiceV2;

    @Test
    void save() throws MessagingException, IOException {
        User user = User.builder()
                .id(1)
                .name("aaaaa")
                .surname("bbbbbbbbbb")
                .email("aaaaaa@mail.ru")
                .bio("ccccccccc")
                .role(Role.USER)
                .lessons(Set.of(Lesson.builder()
                        .id(1)
                        .duration(Duration.MONTHS_3)
                        .price(15.25)
                        .title(Title.JAVA)
                        .isOnline(true)
                        .build()))
                .phoneNumber("0982000")
                .isEnable(true)
                .password("123").build();
        when(userRepository.save(any())).thenReturn(user);
        userServiceV2.save(User.builder()
                .id(1)
                .name("aaaaa")
                .surname("bbbbbbbbbb")
                .email("aaaaaa@mail.ru")
                .bio("ccccccccc")
                .role(Role.USER)
                .lessons(Set.of(Lesson.builder()
                        .id(1)
                        .duration(Duration.MONTHS_3)
                        .price(15.25)
                        .title(Title.JAVA)
                        .isOnline(true)
                        .build()))
                .phoneNumber("0982000")
                .isEnable(true)
                .password("123").build());
        verify(userRepository, times(1)).save(any());


    }
    @Test
    void findById() {
        User user = User.builder()
                .id(1)
                .email("a@mail.com")
                .surname("jenew")
                .build();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userServiceV2.findById(user.getId());
        verify(userRepository).findById(any());
    }

    @Test
    void checkedImage() {
    }

    @Test
    void checkUserEmailAndUserImage() {
    }


    @Test
    void verifyUser() {
    }
}