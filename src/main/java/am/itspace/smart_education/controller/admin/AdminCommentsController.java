package am.itspace.smart_education.controller.admin;

import am.itspace.smart_education.common.entity.Comment;
import am.itspace.smart_education.common.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminCommentsController {

    private final CommentsService commentsService;



    @GetMapping("/comments")
    public String comments(ModelMap modelMap) {
        List<Comment> allComments = commentsService.findAll();
        modelMap.addAttribute("comments", allComments);
        return "comments";
    }

    @GetMapping("/comments/add")
    public String addComments() {
        return "addComments";
    }


    @PostMapping("/comments/add")
    public String addComments(@ModelAttribute Comment comment) {
        commentsService.save(comment);
        return "redirect:/admin/comments";
    }


    @GetMapping("/comments/delete")
    public String deleteComments(@RequestParam("id") int id) {
        commentsService.deleteById(id);
        return "redirect:/admin/user";
    }

    @GetMapping("/comments/update")
    public String update(@ModelAttribute Comment comment) {
        commentsService.save(comment);
        return "redirect:/admin/comments";
    }

}
