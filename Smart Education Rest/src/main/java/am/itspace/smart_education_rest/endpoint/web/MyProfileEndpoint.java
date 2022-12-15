package am.itspace.smart_education_rest.endpoint.web;

import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.security.CurrentUser;
import am.itspace.smart_education_common.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class MyProfileEndpoint {

    private final LessonService lessonService;


    @GetMapping("/my_profile")
    public ResponseEntity<Set<Lesson>> mySubscribe(@AuthenticationPrincipal CurrentUser currentUser) {
        Set<Lesson> all = lessonService.findAllByUser(currentUser);
        return ResponseEntity.ok(all);
    }

}
