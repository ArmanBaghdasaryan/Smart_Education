package am.itspace.smart_education.controller.admin;


import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/lesson")
@RequiredArgsConstructor
public class AdminLessonController {
    private final LessonService lessonService;
    @GetMapping
    public String lesson(ModelMap modelMap) {
        List<Lesson> allLessons = lessonService.findAll();
        modelMap.addAttribute("lessons", allLessons);
        return "admin/admin_lesson";
    }

    @GetMapping("/add")
    public String addLesson() {
        return "admin/addLesson";
    }


    @PostMapping("/add")
    public String addLesson(@ModelAttribute Lesson lesson) {
        lessonService.save(lesson);
        return "redirect:/admin/lesson";
    }


    @GetMapping("/delete/{id}")
    public String deleteLesson(@PathVariable("id") int id) {
        lessonService.deleteById(id);
        return "redirect:/admin/lesson";
    }

    @GetMapping("/update/{id}")
    public String update(ModelMap modelMap,
                         @PathVariable("id") int id) {
        Optional<Lesson> byId = lessonService.findById(id);
        if (byId.isEmpty()) {
            return "redirect:/admin/lesson";
        }
        modelMap.addAttribute("lessons", byId.get());
        return "admin/editLesson";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute Lesson lesson) {
        lessonService.updateLesson(lesson);
        return "redirect:/admin/lesson";
    }

}
