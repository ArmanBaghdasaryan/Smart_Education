package am.itspace.smart_education_rest.endpoint.web;

import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.entity.Role;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.service.LessonService;
import am.itspace.smart_education_common.service.UserService;
import am.itspace.smart_education_rest.dto.LessonsTeachersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PricingEndpoint {

    private final LessonService lessonService;
    private final UserService userService;

    @GetMapping("/pricing")
    public ResponseEntity<LessonsTeachersDto> pricingPage() {
        List<Lesson> last3Lessons = lessonService.findLast3Lessons();
        List<User> allTeacher = userService.findByRole(Role.TEACHER);
        return ResponseEntity.ok(new LessonsTeachersDto(last3Lessons, allTeacher));
    }

}
