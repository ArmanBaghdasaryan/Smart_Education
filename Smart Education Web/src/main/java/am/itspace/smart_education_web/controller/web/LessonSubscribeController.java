package am.itspace.smart_education_web.controller.web;

import am.itspace.smart_education_common.service.LessonService;
import am.itspace.smart_education_common.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/deleteSubscribe/{id}")
    public String deleteSubscribe(@PathVariable("id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
        lessonService.deleteSubscribe(id, currentUser.getUser().getId());
        return "web/myProfile";
    }

}
