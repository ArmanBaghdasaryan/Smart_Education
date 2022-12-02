package am.itspace.smart_education.controller.admin;


import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.service.serviceImpl.LessonServiceImpl;
import am.itspace.smart_education.dto.CreateLessonDto;
import am.itspace.smart_education.dto.RequestAdminLessonDto;
import am.itspace.smart_education.mapper.LessonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/lesson")
@RequiredArgsConstructor
public class AdminLessonController {
    private final LessonServiceImpl lessonServiceImpl;
    private final LessonMapper mapper;

    @GetMapping
    public String lesson(ModelMap modelMap) {
        List<Lesson> allLessons = lessonServiceImpl.findAll();
        modelMap.addAttribute("lessons", allLessons);
        return "admin/admin_lesson";
    }

    @GetMapping("/add")
    public String addLesson() {
        return "admin/addLesson";
    }


    @PostMapping("/add")
    public String addLesson(@ModelAttribute Lesson lesson,
                            @RequestParam("profPic") MultipartFile file) throws IOException {

        lessonServiceImpl.save(lesson, file);
        return "redirect:/admin/user";
    }

    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return lessonServiceImpl.getLessonImage(fileName);

    }

    public String addLesson(@ModelAttribute CreateLessonDto lessonDto) {
        lessonServiceImpl.save(mapper.map(lessonDto));
        return "redirect:/admin/lesson";
    }


    @GetMapping("/delete/{id}")
    public String deleteLesson(@PathVariable("id") int id) {
        lessonServiceImpl.deleteById(id);
        return "redirect:/admin/lesson";
    }

    @GetMapping("/update/{id}")
    public String update(ModelMap modelMap,
                         @PathVariable("id") int id) {
        Optional<Lesson> byId = lessonServiceImpl.findById(id);
        if (byId.isEmpty()) {
            return "redirect:/admin/lesson";
        }
        modelMap.addAttribute("lessons", byId.get());
        return "admin/editLesson";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute RequestAdminLessonDto lessonDto) {
        lessonServiceImpl.updateLesson(mapper.map(lessonDto));
        return "redirect:/admin/lesson";
    }

}
