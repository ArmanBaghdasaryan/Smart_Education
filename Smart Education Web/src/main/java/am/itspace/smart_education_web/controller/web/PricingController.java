package am.itspace.smart_education_web.controller.web;

import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PricingController {

    private final LessonService lessonService;

    @GetMapping("/pricing")
    public String pricingPage(ModelMap modelMap) {
        List<Lesson> last3Lessons = lessonService.findLast3Lessons();
        modelMap.addAttribute("lessonsP", last3Lessons);
        return "web/pricing";
    }

}
