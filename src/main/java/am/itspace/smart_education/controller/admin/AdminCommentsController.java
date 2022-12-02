package am.itspace.smart_education.controller.admin;

import am.itspace.smart_education.common.entity.Comment;
import am.itspace.smart_education.common.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/comments")
@RequiredArgsConstructor
public class AdminCommentsController {

    private final CommentsService commentsService;


    @GetMapping
    public String comments(ModelMap modelMap) {
        List<Comment> allComments = commentsService.findAll();
        modelMap.addAttribute("comments", allComments);
        return "admin/admin_comments";
    }

    @GetMapping("/add")
    public String addComments() {
        return "admin/addComment";
    }


    @PostMapping("/add")
    public String addComments(@ModelAttribute Comment comment) {
        commentsService.save(comment);
        return "redirect:/admin/comments";
    }


    @GetMapping("/delete/{id}")
    public String deleteComments(@PathVariable("id") int id) {
        commentsService.deleteById(id);
        return "redirect:/admin/comments";
    }

    @GetMapping("/update/{id}")
    public String updateComments(ModelMap modelMap,
                                 @PathVariable("id") int id) {
        Optional<Comment> byId = commentsService.findById(id);
        if (byId.isEmpty()) {
            return "redirect:/admin/comment";
        }
        modelMap.addAttribute("comments", byId.get());
        return "admin/editComment";
    }


    @PostMapping("/update")
    public String updateComments(@ModelAttribute Comment comment) {
        commentsService.updateComment(comment);
        return "redirect:/admin/comments";
    }

}
