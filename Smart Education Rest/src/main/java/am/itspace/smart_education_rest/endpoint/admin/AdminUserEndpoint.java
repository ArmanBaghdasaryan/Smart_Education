package am.itspace.smart_education_rest.endpoint.admin;

import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.service.UserService;
import am.itspace.smart_education_rest.service.UserServiceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
@Slf4j
public class AdminUserEndpoint {
    private final UserServiceV2 userServiceV2;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserId(@PathVariable("id") int id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.saveUser(user);
        log.info("User successful created {} ", user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/upload/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {"multipart/form-data"})
    public User register(
            @RequestParam(value = "profPic") MultipartFile file,
            @PathVariable(value = "id") int id
    ) throws MessagingException, IOException {
        User byId = userService.findById(id);
        userServiceV2.checkUserEmailAndUserImage(byId, file);
        userServiceV2.checkedImage(byId, file);
        userServiceV2.save(byId);
        return byId;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable("id") int id) {
        userService.deleteById(id);
        log.info("User with " + id + "id was deleted");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUserId(@PathVariable("id") int id, @RequestBody User user) {
        Optional<User> byUserId = userService.findByUserId(id);
        if (byUserId.isEmpty()) {
            log.error("User with " + id + "id wasn't found");
            return ResponseEntity.badRequest().build();
        }
        user.setId(id);
        userService.saveUser(user);
        log.info("User with id successfully updated: {}", id);
        return ResponseEntity.ok().build();
    }
}
