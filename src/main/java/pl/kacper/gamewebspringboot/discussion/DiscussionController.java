package pl.kacper.gamewebspringboot.discussion;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kacper.gamewebspringboot.comment.Comment;
import pl.kacper.gamewebspringboot.comment.CommentRepository;
import pl.kacper.gamewebspringboot.game.GameRepository;
import pl.kacper.gamewebspringboot.user.CurrentUser;
import pl.kacper.gamewebspringboot.user.UserService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.awt.print.Book;

@Controller
public class DiscussionController {
    private final DiscussionRepository discussionRepository;
    private final GameRepository gameRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;

    public DiscussionController(DiscussionRepository discussionRepository,
                                GameRepository gameRepository,
                                CommentRepository commentRepository,
                                UserService userService) {
        this.discussionRepository = discussionRepository;
        this.gameRepository = gameRepository;
        this.commentRepository = commentRepository;
        this.userService = userService;
    }
    @GetMapping("/admin/add-discussion/{id}")
    public String add(Model model, @PathVariable Long id) {
        model.addAttribute("discussion", new Discussion());
        model.addAttribute("gameId", id);
        return "discussion/add";
    }
    @PostMapping("/admin/add-discussion/{id}")
    public String save(@Valid Discussion discussion, BindingResult result,
                       @PathVariable Long id,
                       @AuthenticationPrincipal CurrentUser user) {
        if (result.hasErrors()){
            return "discussion/add";
        }
        discussion.setUser(user.getUser());
        discussion.setGame(gameRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        discussionRepository.save(discussion);
        return "redirect:/game-list";
    }
    @GetMapping("/discussion/details/{id}")
    public String show(Model model, @PathVariable long id) {
        model.addAttribute("discussion", discussionRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        model.addAttribute("comments", commentRepository.findAllByDiscussion_IdOrderByCreatedOn(id));
        model.addAttribute("comment", new Comment());
        return "discussion/details";
    }
    @PostMapping("/discussion/details/{id}")
    public String save(@Valid Comment comment, BindingResult result, @PathVariable Long id,  @AuthenticationPrincipal CurrentUser user) {
        if (result.hasErrors()){
            return "discussion/details";
        }
        comment.setUser(user.getUser());
        comment.setDiscussion(discussionRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        commentRepository.save(comment);
        return "redirect:/discussion/details/" + id;
    }

}
