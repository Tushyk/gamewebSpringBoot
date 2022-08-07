package pl.kacper.gamewebspringboot.game;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kacper.gamewebspringboot.discussion.DiscussionRepository;
import pl.kacper.gamewebspringboot.rating.Rating;
import pl.kacper.gamewebspringboot.rating.RatingRepository;
import pl.kacper.gamewebspringboot.user.CurrentUser;

import javax.persistence.EntityNotFoundException;

@Controller
public class GameController {
    private final GameRepository gameRepository;
    private final RatingRepository ratingRepository;
    private final DiscussionRepository discussionRepository;

    public GameController(GameRepository gameRepository, RatingRepository ratingRepository, DiscussionRepository discussionRepository) {
        this.gameRepository = gameRepository;
        this.ratingRepository = ratingRepository;
        this.discussionRepository = discussionRepository;
    }
    @GetMapping("/game-list")
    public String list(Model model) {
        model.addAttribute("games", gameRepository.findAll());
        return "game/list";
    }
    @GetMapping("/game-details/{id}")
    public String details(Model model, @PathVariable Long id) {
        model.addAttribute("game", gameRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        model.addAttribute("discussions", discussionRepository.findDiscussionsByGame_Id(id));
        return "game/details";
    }
    @GetMapping("/game-rating/{id}")
    public String rating(@RequestParam String rate, @PathVariable Long id, @AuthenticationPrincipal CurrentUser user, Model model) {
        Rating rating = new Rating();
        double _rate = Double.parseDouble(rate);
        rating.setRating(_rate);
        rating.setGame(gameRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        rating.setUser(user.getUser());
        ratingRepository.save(rating);
        model.addAttribute("rating", rating);
        return "redirect:/game-details";
    }

}
