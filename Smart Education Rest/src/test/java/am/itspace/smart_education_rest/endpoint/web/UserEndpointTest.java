package am.itspace.smart_education_rest.endpoint.web;

import am.itspace.smart_education_common.entity.*;
import am.itspace.smart_education_common.repository.UserRepository;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Set;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserRepository.class)
@AutoConfigureMockMvc(addFilters = false)
class UserEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;


    @After
    public void resetDb() {
        userRepository.deleteAll();
    }
    @Test
    public void register() throws Exception {
        createTestUser(1);
        createTestUser(2);

        mockMvc.perform(get("/users/register").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect((ResultMatcher) jsonPath("$[0].name", is(1)))
                .andExpect((ResultMatcher) jsonPath("$[1].name", is(2)));
    }

    private void createTestUser(int id) {
        User user = User.builder()
                .id(id)
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
        userRepository.saveAndFlush(user);
    }
}


