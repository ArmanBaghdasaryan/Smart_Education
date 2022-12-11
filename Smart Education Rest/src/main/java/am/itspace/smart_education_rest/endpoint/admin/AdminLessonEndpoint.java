package am.itspace.smart_education_rest.endpoint.admin;

import am.itspace.smart_education_common.dto.RequestAdminLessonDto;
import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AdminLessonEndpoint {
    private final LessonService lessonService;
    @GetMapping("/lesson")
    public List<Lesson> getAllLesson(){
        return lessonService.findAll();
    }
    @GetMapping("/lesson/{id}")
    public ResponseEntity<Lesson> getLessonId(@PathVariable("id") int id){
        return lessonService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/lesson")
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson){
        lessonService.save(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/update")
    public ResponseEntity<Lesson> updateLesson(@RequestBody RequestAdminLessonDto lessonDto){
        return ResponseEntity.ok().build();
    }


}
