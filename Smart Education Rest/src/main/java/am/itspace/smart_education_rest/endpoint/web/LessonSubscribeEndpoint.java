package am.itspace.smart_education_rest.endpoint.web;

import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.security.CurrentUser;
import am.itspace.smart_education_common.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/subscribes")
public class LessonSubscribeEndpoint {

    private final LessonService lessonService;

    @GetMapping("/new")
    public ResponseEntity<?> newSubscribe(@RequestParam("lessonId") int lessonId, @AuthenticationPrincipal CurrentUser user) {
        lessonService.subscribe(lessonId, user.getUser().getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubscribe(@PathVariable("id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
        Optional<Lesson> byId = lessonService.findById(id);
        byId.ifPresent(lesson -> {
            lessonService.deleteSubscribe(id, currentUser.getUser().getId());
        });
        return ResponseEntity.noContent().build();
    }

}
