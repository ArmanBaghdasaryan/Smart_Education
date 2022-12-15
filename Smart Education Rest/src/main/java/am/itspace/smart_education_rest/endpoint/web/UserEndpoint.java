package am.itspace.smart_education_rest.endpoint.web;

import am.itspace.smart_education_common.dto.CreateUserDto;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.mapper.UserMapper;
import am.itspace.smart_education_common.service.UserService;
import am.itspace.smart_education_rest.dto.UserAuthDto;
import am.itspace.smart_education_rest.dto.UserAuthResponseDto;
import am.itspace.smart_education_rest.exception.EntityNotFoundException;
import am.itspace.smart_education_rest.service.UserServiceV2;
import am.itspace.smart_education_rest.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
@Service
public class UserEndpoint {

    private final UserServiceV2 userServiceV2;
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;


    @RequestMapping(value = "/register",
            method = RequestMethod.POST)
    public ResponseEntity<User> register(
            @RequestBody CreateUserDto userDto
    ) throws MessagingException, IOException {
        Optional<User> existingUser = userService.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(userServiceV2.save(userMapper.map(userDto)));
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


    @PostMapping("/auth")
    public ResponseEntity<UserAuthResponseDto> auth(
            @RequestBody UserAuthDto userAuthDto) {
        Optional<User> byEmail = userService.findByEmail(userAuthDto.getEmail());
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            if (passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword())) {
                log.info("User with  username {} get auth token", user.getEmail());
                return ResponseEntity.ok(UserAuthResponseDto.builder()
                        .token(jwtTokenUtil.generateToken(user.getEmail()))
                        .userId(user.getId())
                        .build()
                );
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/verify")
    public boolean verifyUser(
            @RequestParam("email") String email,
            @RequestParam("token") String token
    ) throws Exception {
        User user = userServiceV2.verifyUser(email, token);
        return user.isEnable();
    }
}
