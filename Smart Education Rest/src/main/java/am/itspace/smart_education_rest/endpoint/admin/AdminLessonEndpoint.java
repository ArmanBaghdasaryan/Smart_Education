package am.itspace.smart_education_rest.endpoint.admin;

import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class AdminLessonEndpoint {

    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLesson() {
        return ResponseEntity.ok(lessonService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonId(@PathVariable("id") int id) {
        return lessonService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) {
        lessonService.save(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/upload/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {"multipart/form-data"})
    public Lesson createLessonImage(@RequestParam("fileName") MultipartFile multipartFile,
                                    @PathVariable("id") int id) throws IOException {
        Optional<Lesson> byId = lessonService.findById(id);
        if (byId.isEmpty()) {
            return null;
        }
        lessonService.save(byId.get(), multipartFile);
        return byId.get();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLessonById(@PathVariable("id") int id) {
        lessonService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable("id") int id, @RequestBody Lesson lesson) {
        Optional<Lesson> byId = lessonService.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        lesson.setId(id);
        lessonService.updateLesson(lesson);
        return ResponseEntity.ok().build();
    }


}
