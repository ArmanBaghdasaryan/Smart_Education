package am.itspace.smart_education_rest.endpoint.web;

import am.itspace.smart_education_common.entity.Duration;
import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.entity.Title;
import am.itspace.smart_education_common.repository.LessonRepository;
import am.itspace.smart_education_common.service.LessonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class LessonEndpointTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LessonRepository lessonRepository;

    @Autowired
    private CourseEndpoint courseEndpoint;

    @Autowired
    private LessonService lessonService;


    @Test
    public void findAllLessons() throws Exception {
        lessonRepository.save(lessonList());
        mvc.perform(get("/courses")
                        .content(asJsonString(lessonList()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        Assertions.assertNotEquals(lessonList(), lessonRepository.findAll());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Lesson lessonList() {

        return Lesson.builder()
                        .title(Title.JS)
                        .isOnline(true)
                        .price(15.5)
                        .duration(Duration.MONTHS_3)
                        .id(1).build();

    }
}
