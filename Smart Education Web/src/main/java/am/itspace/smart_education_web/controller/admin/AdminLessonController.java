package am.itspace.smart_education_web.controller.admin;


import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.service.LessonService;
import am.itspace.smart_education_common.dto.CreateLessonDto;
import am.itspace.smart_education_common.dto.RequestAdminLessonDto;
import am.itspace.smart_education_common.mapper.LessonMapper;
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

    private final LessonService lessonService;
    private final LessonMapper mapper;

    @GetMapping
    public String lesson(ModelMap modelMap) {
        List<Lesson> allLessons = lessonService.findAll();
        modelMap.addAttribute("lessons", allLessons);
        return "admin/admin_lesson";
    }

    @GetMapping("/add")
    public String addLesson(ModelMap modelMap) {
        modelMap.addAttribute("lesson", new CreateLessonDto());
        return "admin/addLesson";
    }


    @PostMapping("/add")
    public String addLesson(@ModelAttribute CreateLessonDto lessonDto,
                            @RequestParam("profPic") MultipartFile file) throws IOException {
        lessonService.save(mapper.map(lessonDto), file);
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
        byId.ifPresent(lesson -> modelMap.addAttribute("lessons", lesson));
        return "admin/editLesson";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute RequestAdminLessonDto lessonDto) {
        lessonService.updateLesson(mapper.map(lessonDto));
        return "redirect:/admin/lesson";
    }

    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return lessonService.getLessonImage(fileName);

    }


}
