package am.itspace.smart_education_rest.service.impl;


import am.itspace.smart_education_common.entity.Duration;
import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.entity.Title;
import am.itspace.smart_education_common.repository.LessonRepository;
import am.itspace.smart_education_common.service.LessonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@SpringBootTest
public class LessonServiceTest {

    @MockBean
    private LessonRepository lessonRepository;

    @Autowired
    private LessonService lessonService;

    @Test
    void save() {
        when(lessonRepository.save(any())).thenReturn(buildLesson());
        Lesson lesson = buildLesson();
        lesson.setId(0);
        lessonService.save(lesson);
        verify(lessonRepository, times(1)).save(any());

    }

    @Test
    void updateLesson() {
        when(lessonRepository.save(any())).thenReturn(buildLesson());
        Lesson lesson = buildLesson();
        lesson.setId(1);
        lessonService.updateLesson(lesson);
        verify(lessonRepository, times(1)).save(any());

    }

    @Test
    void findById() {
        Lesson lesson = buildLesson();
        when(lessonRepository.findById(any())).thenReturn(Optional.of(lesson));
        Optional<Lesson> byId = lessonService.findById(lesson.getId());
        verify(lessonRepository).findById(any());
        assertTrue(byId.isPresent());
        assertEquals(byId.get().getId(), lesson.getId());
    }

    @Test
    void findAll() {
        Lesson lesson = buildLesson();
        List<Lesson> lessonList = List.of(lesson);
        when(lessonRepository.findAll()).thenReturn(lessonList);
        List<Lesson> all = lessonService.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    void findLast3Lessons() {
        Lesson lesson = buildLesson();
        List<Lesson> lessonList = List.of(lesson);
        when(lessonRepository.findTop3ByOrderByIdAsc()).thenReturn(lessonList);
        List<Lesson> last3Lessons = lessonService.findLast3Lessons();
        assertThat(last3Lessons.size()).isEqualTo(1);
    }

    @Test
    void deleteById() {
        // First save lesson then remove
        when(lessonRepository.save(any())).thenReturn(buildLesson());
        when(lessonRepository.findById(any())).thenReturn(Optional.empty());
        Lesson lesson = buildLesson();
        lesson.setId(0);
        lessonService.save(lesson);
        verify(lessonRepository, times(1)).save(any());
        lessonService.deleteById(lesson.getId());
        Optional<Lesson> byId = lessonRepository.findById(lesson.getId());
        assertTrue(byId.isEmpty());
    }

    private static Lesson buildLesson() {
        return Lesson.builder()
                .id(1)
                .title(Title.JAVA)
                .price(3500)
                .duration(Duration.MONTHS_7)
                .totalHours(1500)
                .build();
    }
}
