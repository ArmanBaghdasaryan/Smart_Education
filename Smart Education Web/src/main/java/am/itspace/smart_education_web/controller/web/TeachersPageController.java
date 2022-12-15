package am.itspace.smart_education_web.controller.web;

import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TeachersPageController {

    private final UserService userService;

    @GetMapping("/teachersPage")
    public String teachers() {
        return "web/teachersPage";
    }

    @GetMapping("/teachers/{id}")
    public String teacherSinglePage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<User> byId = Optional.ofNullable(userService.findById(id));
        if (byId.isEmpty()) {
            return "redirect:/";
        }
        modelMap.addAttribute("teachersPage", byId.get());
        return "web/teachersPage";
    }

}
