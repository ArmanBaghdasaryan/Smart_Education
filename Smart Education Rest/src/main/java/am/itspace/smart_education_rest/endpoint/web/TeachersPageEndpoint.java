package am.itspace.smart_education_rest.endpoint.web;


import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.service.UserService;
import am.itspace.smart_education_rest.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeachersPageEndpoint {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> teacherSinglePage(@PathVariable("id") int id) throws EntityNotFoundException {
        User byId = Optional.ofNullable(userService.findById(id)).orElseThrow(
                () -> new EntityNotFoundException("User with " + id + "id does not exits")
        );
        return ResponseEntity.ok(byId);
    }
}
