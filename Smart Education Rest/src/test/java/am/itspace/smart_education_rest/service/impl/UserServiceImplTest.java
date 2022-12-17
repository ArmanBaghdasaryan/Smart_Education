package am.itspace.smart_education_rest.service.impl;

import am.itspace.smart_education_common.entity.*;
import am.itspace.smart_education_common.repository.UserRepository;
import am.itspace.smart_education_common.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;

    @Test
    void save() throws MessagingException, IOException {
        userService.save(user(),
                new MockMultipartFile("image", new ByteArrayInputStream("AAAAAAAA".getBytes(StandardCharsets.UTF_8))));
        verify(userRepository, times(1)).save(any());

    }

    @Test
    void update() throws IOException {
        when(userRepository.save(any())).thenReturn(user());
        userService.updateUser(User.builder()
                        .id(1)
                        .name("Uname")
                        .surname("USurname")
                        .email("updated@mail.ru")
                        .bio("user")
                        .role(Role.USER)
                        .lessons(Set.of(Lesson.builder()
                                .id(1)
                                .duration(Duration.MONTHS_7)
                                .price(15.25)
                                .title(Title.C_PLUS)
                                .isOnline(true)
                                .build()))
                        .phoneNumber("0982000")
                        .isEnable(true)
                        .password("111").build(),
                new MockMultipartFile("image", new ByteArrayInputStream("AAAAAAAA".getBytes(StandardCharsets.UTF_8))));
        verify(userRepository, times(1)).save(any());

    }

    @Test
    void findById() {
        when(userRepository.findById(user().getId())).thenReturn(Optional.of(user()));
        userService.findById(user().getId());
        verify(userRepository).findById(any());
    }

    @Test
    void findAll() {
        when(userRepository.findAll()).thenThrow(new EntityNotFoundException("User not found"));
        assertThrows(EntityNotFoundException.class, userService::findAll);
        verify(userRepository).findAll();
    }

    @Test
    public void testDeleteUserById() {
        when(userRepository.findById(user().getId())).thenReturn(Optional.of(user()));
        userService.deleteById(user().getId());
        verify(userRepository).deleteById(user().getId());
    }

    @Test
    void findUserByRole() {
        when(userRepository.findAllByRole(user().getRole())).thenReturn(List.of(user()));
        userService.findByRole(Role.USER);
        verify(userRepository).findAllByRole(Role.USER);
    }

    @Test
    void checkedImage() throws IOException {
        userService.checkedImage(user(),
                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes(StandardCharsets.UTF_8))));

    }

    @Test
    void testCheckUserEmailAndUserImage() throws IOException {
        when(userRepository.findByEmail((user().getEmail()))).thenReturn(Optional.of(user()));
        MockMultipartFile file = new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes(StandardCharsets.UTF_8)));
        assertTrue(userService.checkUserEmailAndUserImage(user(), file, new ModelMap()));
        verify(userRepository).findByEmail(((user().getEmail())));
    }

    //    @Test
//    void pageable(){
//        List<User> users = new ArrayList<>();
//        Page<User> userPage = new PageImpl<>(users);
//        Mockito.when(this.userRepository.findAll(userPage.getPageable())).thenReturn(userPage);
//        Pageable pageRequest = PageRequest.of(0, 4);
//        List<User> userList = userService.findUsersWithPage(pageRequest).map(user -> {
//            user();
//        });
//        assertSame(userList.size(),4);
//        verify(userRepository).findAll();
//    }
    @Test
    void testDeleteById() {

        Optional<User> ofResult = Optional.of(user());
        doNothing().when(userRepository).deleteById(Mockito.any());
        when(userRepository.findById(Mockito.any())).thenReturn(ofResult);
        userService.deleteById(1);
        verify(userRepository).deleteById(Mockito.any());
    }

    @Test
    void testDeleteById2() {
        Optional<User> ofResult = Optional.of(user());
        doThrow(new EntityNotFoundException("user not found")).when(userRepository)
                .deleteById(Mockito.any());
        when(userRepository.findById(Mockito.any())).thenReturn(ofResult);
        assertThrows(EntityNotFoundException.class, () -> userService.deleteById(1));
        verify(userRepository).deleteById(Mockito.any());
    }

    private User user() {
        return User.builder()
                .id(1)
                .name("name")
                .surname("surname")
                .email("arman@mail.ru")
                .bio("teacher")
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
                .verifyToken("12345")
                .password("123").build();
    }
}