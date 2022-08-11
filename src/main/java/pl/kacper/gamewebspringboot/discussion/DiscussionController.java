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
import pl.kacper.gamewebspringboot.user.UserRepository;
import pl.kacper.gamewebspringboot.user.UserService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Objects;

@Controller
public class DiscussionController {
    private final DiscussionRepository discussionRepository;
    private final GameRepository gameRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public DiscussionController(DiscussionRepository discussionRepository,
                                GameRepository gameRepository,
                                CommentRepository commentRepository,
                                UserService userService,
                                UserRepository userRepository) {
        this.discussionRepository = discussionRepository;
        this.gameRepository = gameRepository;
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.userRepository = userRepository;
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
        user.getUser().setNumberOfDiscussions(discussionRepository.countDiscussionsByUser_Id(user.getUser().getId()));
        userRepository.save(user.getUser());
        return "redirect:/game-details/" + id;
    }
    @GetMapping("/discussion/details/{id}")
    public String show(Model model, @PathVariable long id, @AuthenticationPrincipal CurrentUser user) {
        model.addAttribute("discussion", discussionRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        model.addAttribute("comments", commentRepository.findAllByDiscussion_Id(id));
        model.addAttribute("comment", new Comment());
        if (user != null) {
            model.addAttribute("currentUser", user.getUser());
        }
        return "discussion/details";
    }
    @PostMapping("/discussion/details/{id}")
    public String save(@Valid Comment comment, BindingResult result, @PathVariable Long id,  @AuthenticationPrincipal CurrentUser user) {
        if (result.hasErrors()){
            return "discussion/details";
        }
        comment.setUser(user.getUser());
        comment.setDiscussion(discussionRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        comment.setCreatedOn(LocalDateTime.now());
        commentRepository.save(comment);
        return "redirect:/discussion/details/" + id;
    }
    @GetMapping("admin/discussion/confirm-delete/{id}")
    public String confirm(Model model, @PathVariable long id) {
        Discussion discussion = discussionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("gameId", discussion.getGame().getId());
        model.addAttribute("discussionId", discussion.getId());
        return "discussion/confirmDelete";
    }
    @GetMapping("admin/discussion/delete/{id}")
    public String delete( @PathVariable long id, @AuthenticationPrincipal CurrentUser user) {
        Discussion discussion = discussionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (Objects.equals(discussion.getUser().getId(), user.getUser().getId())){
            commentRepository.deleteAllByDiscussion_Id(id);
            return "redirect:/discussion/details/" + id;
        }
        return "redirect:/admin/books/all";
    }
    @GetMapping("/admin/discussion/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        model.addAttribute("discussion", discussionRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        return "discussion/edit";
    }
    @PostMapping("admin/discussion/update")
    public String update(@Valid Discussion discussion, BindingResult result) {
        if (result.hasErrors()){
            return "discussion/edit";
        }
        discussionRepository.save(discussion);
        return "redirect:/discussion/details/" + discussion.getId();
    }
}
