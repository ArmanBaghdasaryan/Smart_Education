package am.itspace.smart_education_rest.endpoint.web;

import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.entity.Role;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.service.LessonService;
import am.itspace.smart_education_common.service.UserService;
import am.itspace.smart_education_rest.dto.UsersLessonsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainEndpoint {

    private final LessonService lessonService;
    private final UserService userService;


    @GetMapping("/")
    public ResponseEntity<UsersLessonsDto> home() {
        List<Lesson> allLesson = lessonService.findLast3Lessons();
        List<User> allTeacher = userService.findByRole(Role.TEACHER);
        return ResponseEntity.ok(new UsersLessonsDto(allTeacher, allLesson));
    }
}
