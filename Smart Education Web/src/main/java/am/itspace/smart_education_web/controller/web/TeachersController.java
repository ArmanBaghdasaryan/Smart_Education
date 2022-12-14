package am.itspace.smart_education_web.controller.web;

import am.itspace.smart_education_common.entity.Role;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TeachersController {

    private final UserService userService;


    @GetMapping("/teachers")
    public String teacherSinglePage(ModelMap modelMap) {
        List<User> allTeacher = userService.findByRole(Role.TEACHER);
        modelMap.addAttribute("teachers", allTeacher);
        return "web/teachers";

    }

    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String file) throws IOException {
        return userService.getUserImage(file);
    }
}
