package am.itspace.smart_education.controller.web;

import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.service.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TeachersPageController {

    private final UserServiceImpl userService;

    @GetMapping("/teachersPage")
    public String teachers() {
        return "web/teachersPage";
    }

    @GetMapping("/teachers/{id}")
    public String bookSinglePage(@PathVariable("id") int id, ModelMap modelMap)  {
        Optional<User> byId = Optional.ofNullable(userService.findById(id));
        if (byId.isEmpty()) {
            return "redirect:/";
        }
        modelMap.addAttribute("teachersPage", byId.get());
        return "web/teachersPage";
    }

}
