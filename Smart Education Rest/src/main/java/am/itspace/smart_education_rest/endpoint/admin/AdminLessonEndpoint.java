package am.itspace.smart_education_rest.endpoint.admin;

import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        log.info("Lesson successful created {} ", lesson);
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
            log.error("Lesson with " + id + "id wasn't found");
            return null;
        }
        lessonService.save(byId.get(), multipartFile);
        log.info("Lesson with id successfully updated: {}", id);
        return byId.get();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLessonById(@PathVariable("id") int id) {
        lessonService.deleteById(id);
        log.info("lesson with " + id + "id was deleted");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable("id") int id, @RequestBody Lesson lesson) {
        Optional<Lesson> byId = lessonService.findById(id);
        if (byId.isEmpty()) {
            log.error("Lesson with " + id + "id wasn't found");
            return ResponseEntity.badRequest().build();
        }
        lesson.setId(id);
        lessonService.updateLesson(lesson);
        log.info("Lesson with id successfully updated: {}", id);
        return ResponseEntity.ok().build();

    }
}
