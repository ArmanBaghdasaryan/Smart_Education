package am.itspace.smart_education_web.controller.web;

import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.entity.Role;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.service.LessonService;
import am.itspace.smart_education_common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PricingController {

    private final LessonService lessonService;
    private final UserService userService;

    @GetMapping("/pricing")
    public String pricingPage(ModelMap modelMap) {
        List<Lesson> last3Lessons = lessonService.findLast3Lessons();
        List<User> allTeacher = userService.findByRole(Role.TEACHER);
        modelMap.addAttribute("lessonsP", last3Lessons);
        modelMap.addAttribute("teachersPricing", allTeacher);
        return "web/pricing";
    }

}
