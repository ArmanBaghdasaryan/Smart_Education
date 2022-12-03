package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.service.serviceImpl.LessonServiceImpl;
import am.itspace.smart_education.common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CourseController {
private final LessonServiceImpl lessonServiceImpl;
private final UserService userService;
    @GetMapping("/course")
    public String bookSinglePage(ModelMap modelMap) {
        List<Lesson> allLesson = lessonServiceImpl.findAll();
        modelMap.addAttribute("course", allLesson);
        return "web/course";

    }
    @GetMapping(value = "/getPic", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return lessonServiceImpl.getLessonImage(fileName);
    }
}
