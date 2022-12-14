package am.itspace.smart_education_web.controller.web;

import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.security.CurrentUser;
import am.itspace.smart_education_common.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class MyProfileController {

    private final LessonService lessonService;

    @GetMapping("/my_profile")
    public String mySubscribe(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        Set<Lesson> all = lessonService.findAllByUser(currentUser);
        modelMap.addAttribute("mySub", all);
        return "web/myProfile";
    }

}

