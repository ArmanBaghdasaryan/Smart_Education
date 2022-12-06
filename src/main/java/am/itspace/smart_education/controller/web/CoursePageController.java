package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CoursePageController {

    private final LessonService lessonService;

    @GetMapping("/coursePage")
    public String course() {
        return "web/coursePage";
    }

    @GetMapping("/course/{id}")
    public String courseSinglePage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Lesson> byId = lessonService.findById(id);
        if (byId.isEmpty()) {
            return "redirect:/";
        }
        modelMap.addAttribute("coursePage", byId.get());
        return "web/coursePage";
    }


}
