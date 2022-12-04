package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.repository.LessonRepository;
import am.itspace.smart_education.common.service.LessonService;
import am.itspace.smart_education.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyProfileController {
    private final LessonService lessonService;


    @GetMapping("/my_profile")
    public String mySubscribe(ModelMap modelMap) {
        List<Lesson> all = lessonService.findAll();
        modelMap.addAttribute("mySub", all);
        return "web/myProfile";
    }
}

