package am.itspace.smart_education_web.controller.web;

import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.service.LessonService;
import am.itspace.smart_education_common.service.UserService;
import am.itspace.smart_education_common.security.CurrentUser;
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
    private final UserService userService;


    @GetMapping("/my_profile")
    public String mySubscribe(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        Set<Lesson> all = lessonService.findAllByUser(currentUser);
//        User byId = userService.findById(currentUser.getUser().getId());
        modelMap.addAttribute("mySub", all);
        return "web/myProfile";
    }

}

