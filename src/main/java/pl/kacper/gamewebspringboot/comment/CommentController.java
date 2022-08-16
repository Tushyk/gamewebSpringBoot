package pl.kacper.gamewebspringboot.comment;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kacper.gamewebspringboot.discussion.Discussion;
import pl.kacper.gamewebspringboot.user.CurrentUser;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Controller
public class CommentController {
    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    @GetMapping("admin/comment/confirm-delete/{id}")
    public String confirmDeleteComment(Model model, @PathVariable long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("discussionId", comment.getDiscussion().getId());
        model.addAttribute("commentId", comment.getId());
        return "comment/confirmDelete";
    }
    @GetMapping("admin/comment/delete/{id}")
    public String deleteComment( @PathVariable long id, @AuthenticationPrincipal CurrentUser user) {
        Comment comment = commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (Objects.equals(comment.getUser().getId(), user.getUser().getId()) || user.getUser().getId() == 1){
            commentRepository.delete(comment);
            return "redirect:/discussion/details/" + comment.getDiscussion().getId();
        }
        return "redirect:/admin/books/all";
    }
    @GetMapping("/admin/comment/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        model.addAttribute("comment", commentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        return "comment/edit";
    }
    @PostMapping("admin/comment/update")
    public String update(@Valid Comment comment, BindingResult result) {
        if (result.hasErrors()){
            return "comment/edit";
        }
        comment.setUpdatedOn(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        commentRepository.save(comment);
        return "redirect:/discussion/details/" + comment.getDiscussion().getId();
    }
}
