package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.entity.Role;
import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CourseController {
private final LessonService lessonService;
    @GetMapping("/course")
    public String bookSinglePage(ModelMap modelMap) {
        List<Lesson> allLesson = lessonService.findAll();
        if (allLesson.isEmpty()) {
            return "redirect:/";
        }
        modelMap.addAttribute("course", allLesson);
        return "web/course";

    }
}
