package am.itspace.smart_education_rest.endpoint.web;

import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseEndpoint {

    private final LessonService lessonService;


    @GetMapping
    public ResponseEntity<List<Lesson>> showAllLessons() {
        List<Lesson> allLesson = lessonService.findAll();
        return ResponseEntity.ok(allLesson);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> courseSinglePage(@PathVariable("id") int id) {
        Optional<Lesson> byId = lessonService.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId.get());
    }

    @GetMapping(value = "/getPic", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        return lessonService.getLessonImage(fileName);
    }
}
