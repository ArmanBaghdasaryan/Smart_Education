package am.itspace.smart_education.controller.admin;

import am.itspace.smart_education.common.entity.Comment;
import am.itspace.smart_education.common.services.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminCommentsController {

    private final CommentsService commentsService;



    @GetMapping("/admin/comments")
    public String comments(ModelMap modelMap) {
        List<Comment> allComments = commentsService.findAll();
        modelMap.addAttribute("comments", allComments);
        return "comments";
    }

    @GetMapping("/admin/comments/add")
    public String addComments() {
        return "addComments";
    }


    @PostMapping("/admin/comments/add")
    public String addComments(@ModelAttribute Comment comment) {
        commentsService.save(comment);
        return "redirect:/admin/comments";
    }


    @GetMapping("/admin/comments/delete")
    public String deleteComments(@RequestParam("id") int id) {
        commentsService.deleteById(id);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/comments/update")
    public String update(@ModelAttribute Comment comment) {
        commentsService.save(comment);
        return "redirect:/admin/comments";
    }

}
