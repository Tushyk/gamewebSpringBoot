package pl.kacper.gamewebspringboot.game;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("ratings", ratingRepository.findRatingsOrdered());
        model.addAttribute("games", gameRepository.findGamesOrdered());
        return "game/list";
    }
    @GetMapping("/gameList")
    public String gameDetails(@RequestParam String title) {
        if (gameRepository.findGameByTitleIgnoreCase(title) == null) {
            return "redirect:/game-list";
        } else {
            return "redirect:/game-details/" + gameRepository.findGameByTitleIgnoreCase(title).getId();
        }
    }
    @GetMapping("/game-details/{id}")
    public String details(Model model, @PathVariable Long id, @AuthenticationPrincipal CurrentUser user) {
        model.addAttribute("game", gameRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        model.addAttribute("discussions", discussionRepository.findDiscussionsByGame_Id(id));
        if (user != null) {
            model.addAttribute("rating", ratingRepository.findRatingByUser_IdAndAndGame_Id(user.getUser().getId(), id));
            model.addAttribute("currentUser", user.getUser());
        }
        model.addAttribute("avgRate", ratingRepository.getAvgRating(id));
        return "game/details";
    }
    @GetMapping("/game-rating/{id}")
    public String rating(@RequestParam String rate, @PathVariable Long id, @AuthenticationPrincipal CurrentUser user, Model model) {
        if (ratingRepository.findRatingByUser_IdAndAndGame_Id(user.getUser().getId(), id) == null) {
            Rating rating = new Rating();
            double _rate = Double.parseDouble(rate);
            rating.setRating(_rate);
            rating.setGame(gameRepository.findById(id).orElseThrow(EntityNotFoundException::new));
            rating.setUser(user.getUser());
            ratingRepository.save(rating);
            Game game = gameRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            game.setAvgRating(ratingRepository.getAvgRating(id));
            game.setNumberOfRatings(ratingRepository.numberOfRatingsPerGame(id));
            gameRepository.save(game);
            return "redirect:/game-details/" + id;
        } else {
            model.addAttribute("rating", ratingRepository.findRatingByUser_IdAndAndGame_Id(user.getUser().getId(), id));
            return "game/rating-edit";
        }
    }
    @GetMapping("/rating-update/{id}")
    public String updateRating(@PathVariable Long id, @RequestParam String rate){
        Rating rating = ratingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        rating.setRating(Double.parseDouble(rate));
        ratingRepository.save(rating);
        Game game = rating.getGame();
        game.setAvgRating(ratingRepository.getAvgRating(id));
        gameRepository.save(game);
        return "redirect:/game-details/" + rating.getGame().getId();
    }

}