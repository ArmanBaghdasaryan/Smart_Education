package am.itspace.smart_education_web.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompilerController {

    @GetMapping("/compiler")
    public String compiler(@RequestParam(required = false) String programLang, ModelMap map) {
        map.addAttribute("programLang", programLang);
        return "web/compiler";
    }
}
