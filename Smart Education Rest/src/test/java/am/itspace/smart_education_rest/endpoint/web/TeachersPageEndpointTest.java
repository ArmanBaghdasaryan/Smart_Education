package am.itspace.smart_education_rest.endpoint.web;

import am.itspace.smart_education_common.entity.Role;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.repository.UserRepository;
import am.itspace.smart_education_common.service.UserService;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
class TeachersPageEndpointTest {
    @Autowired
    private TeachersPageEndpoint teachersPageEndpoint;

    @Autowired
    private UserRepository userRepository;
    @MockBean
    private UserService userService;

    @After
    public void resetDb() {
        userRepository.deleteAll();
    }

    @Test
    void testTeacherSinglePage() throws Exception {
        when(userService.findById(anyInt())).thenReturn(buildTeacher());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/teachers/{id}", 1);
        MockMvcBuilders.standaloneSetup(teachersPageEndpoint)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("name"));
    }


    private User buildTeacher() {
        return User.builder()
                .id(1)
                .name("name")
                .surname("surname")
                .email("email.email.ru")
                .bio("bio")
                .verifyToken("token")
                .isEnable(true)
                .phoneNumber("077852")
                .role(Role.TEACHER)
                .build();
    }
}

