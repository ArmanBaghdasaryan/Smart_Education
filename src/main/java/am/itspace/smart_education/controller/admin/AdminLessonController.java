package am.itspace.smart_education.controller.admin;


import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminLessonController {
    private final LessonService lessonService;
    @GetMapping("/lesson")
    public String lesson(ModelMap modelMap) {
        List<Lesson> allLessons = lessonService.findAll();
        modelMap.addAttribute("lessons", allLessons);
        return "lessons";
    }

    @GetMapping("/lesson/add")
    public String addLesson() {
        return "addLesson";
    }


    @PostMapping("/lesson/add")
    public String addLesson(@ModelAttribute Lesson lesson) {
        lessonService.save(lesson);
        return "redirect:/admin/lesson";
    }


    @GetMapping("/lesson/delete")
    public String deleteLesson(@RequestParam("id") int id) {
        lessonService.deleteById(id);
        return "redirect:/admin/lesson";
    }

    @GetMapping("/lesson/update")
    public String update(@ModelAttribute Lesson lesson) {
        lessonService.save(lesson);
        return "redirect:/admin/lesson";
    }

}
