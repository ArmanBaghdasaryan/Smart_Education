package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.service.serviceImpl.LessonServiceImpl;
import am.itspace.smart_education.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LessonSubscribeController {

    private final LessonServiceImpl lessonServiceImpl;

    @GetMapping("/subscribe/new")
    public String newSubscribe(@RequestParam("lessonId") int lessonId, @AuthenticationPrincipal CurrentUser user) {

        lessonServiceImpl.subscribe(lessonId, user.getUser().getId());
        return "redirect:/";
    }
}
