package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.service.LessonService;
import am.itspace.smart_education.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LessonSubscribeController {

    private final LessonService lessonService;

    @GetMapping("/subscribe/new")
    public String newSubscribe(@RequestParam("lessonId") int lessonId, @AuthenticationPrincipal CurrentUser user) {
        lessonService.subscribe(lessonId, user.getUser().getId());
        return "redirect:/";
    }
}
