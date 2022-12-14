package am.itspace.smart_education_rest.endpoint.admin;

import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.mapper.UserMapper;
import am.itspace.smart_education_common.service.UserService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/users")
public class AdminUserEndpoint {
    private final UserService userService;
    private final UserMapper userMapper;

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
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/upload/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {"multipart/form-data"})
    public User createUserImage(@RequestParam("fileName") MultipartFile multipartFile,
                                @PathVariable("id") int id) throws IOException, MessagingException {
        User byId = userService.findById(id);
        if (byId == null) {
            return null;
        }
        userService.save(byId, multipartFile);
        return byId;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable("id") int id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUserId(@PathVariable("id") int id, @RequestBody User user) {
        Optional<User> byUserId = userService.findByUserId(id);
        if (byUserId.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        user.setId(id);
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }
}
