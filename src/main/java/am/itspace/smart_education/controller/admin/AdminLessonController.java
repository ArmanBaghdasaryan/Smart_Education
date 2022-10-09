package am.itspace.smart_education.controller.admin;


import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminLessonController {

    private final LessonService lessonService;

    @GetMapping("/admin/lesson")
    public String lessonHome() {
        return "lesson";
    }

    @GetMapping("/admin/lesson")
    public String lesson(ModelMap modelMap) {
        List<Lesson> allLessons = lessonService.findAll();
        modelMap.addAttribute("lessons", allLessons);
        return "lessons";
    }

    @GetMapping("/admin/lesson/add")
    public String addLesson() {
        return "addLesson";
    }


    @PostMapping("/admin/lesson/add")
    public String addLesson(@ModelAttribute Lesson lesson) {
        lessonService.save(lesson);
        return "redirect:/admin/lesson";
    }


    @GetMapping("/admin/lesson/delete")
    public String deleteLesson(@RequestParam("id") int id) {
        lessonService.deleteById(id);
        return "redirect:/admin/lesson";
    }

    @GetMapping("/admin/lesson/update")
    public String update(@ModelAttribute Lesson lesson) {
        lessonService.save(lesson);
        return "redirect:/admin/lesson";
    }

}
