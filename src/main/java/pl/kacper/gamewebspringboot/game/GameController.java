package pl.kacper.gamewebspringboot.game;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kacper.gamewebspringboot.comment.CommentRepository;
import pl.kacper.gamewebspringboot.discussion.Discussion;
import pl.kacper.gamewebspringboot.discussion.DiscussionRepository;
import pl.kacper.gamewebspringboot.genre.GenreRepository;
import pl.kacper.gamewebspringboot.platform.PlatformRepository;
import pl.kacper.gamewebspringboot.publisher.PublisherRepository;
import pl.kacper.gamewebspringboot.rating.Rating;
import pl.kacper.gamewebspringboot.rating.RatingRepository;
import pl.kacper.gamewebspringboot.user.CurrentUser;
import pl.kacper.gamewebspringboot.user.UserRepository;
import pl.kacper.gamewebspringboot.user.UserService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;


@Controller
public class GameController {
    private final GameRepository gameRepository;
    private final RatingRepository ratingRepository;
    private final DiscussionRepository discussionRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;
    private final PlatformRepository platformRepository;
    private final CommentRepository commentRepository;

    public GameController(GameRepository gameRepository, RatingRepository ratingRepository, DiscussionRepository discussionRepository, UserService userService, UserRepository userRepository, GenreRepository genreRepository, PublisherRepository publisherRepository, PlatformRepository platformRepository, CommentRepository commentRepository) {
        this.gameRepository = gameRepository;
        this.ratingRepository = ratingRepository;
        this.discussionRepository = discussionRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;
        this.platformRepository = platformRepository;
        this.commentRepository = commentRepository;
    }
    @GetMapping("/game-list")
    public String list(Model model) {
        model.addAttribute("ratings", ratingRepository.findRatingsOrdered());
        model.addAttribute("games", gameRepository.findGamesOrdered());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("platforms", platformRepository.findAll());
        return "game/list";
    }
    @GetMapping("/game-list-specify")
    public String specifyList(Model model, @RequestParam String genre, @RequestParam String year,@RequestParam String platform) {
        if (genre.equals("default") && year.equals("default") && platform.equals("default")){
            model.addAttribute("games", gameRepository.findGamesOrdered());
        } else if (!genre.equals("default") && year.equals("default") && platform.equals("default")) {
            model.addAttribute("games", gameRepository.findGamesByGenre(Math.toIntExact(genreRepository.findGenreByName(genre).getId())));
            model.addAttribute("genre", genreRepository.findGenreByName(genre));
        } else if (genre.equals("default") && !year.equals("default") && platform.equals("default")) {
            String begin = year + "0101";
            int beginYear = Integer.parseInt(begin);
            String end = year + "1224";
            int endYear = Integer.parseInt(end);
            model.addAttribute("games", gameRepository.findGamesByDate(beginYear, endYear));
            model.addAttribute("year", year);
        } else if (genre.equals("default") && year.equals("default") && !platform.equals("default")) {
            model.addAttribute("games", gameRepository.findGamesByPlatform(Math.toIntExact(platformRepository.findPlatformByName(platform).getId())));
            model.addAttribute("platform", platformRepository.findPlatformByName(platform));
        } else if (!genre.equals("default") && !year.equals("default") && platform.equals("default")) {
            String begin = year + "0101";
            int beginYear = Integer.parseInt(begin);
            String end = year + "1224";
            int endYear = Integer.parseInt(end);
            int genreId = Math.toIntExact(genreRepository.findGenreByName(genre).getId());
            model.addAttribute("games", gameRepository.findGamesByGenreAndDate(genreId, beginYear, endYear));
            model.addAttribute("genre", genreRepository.findGenreByName(genre));
            model.addAttribute("year", year);
        } else if (!genre.equals("default") && year.equals("default") && !platform.equals("default")) {
            int genreId = Math.toIntExact(genreRepository.findGenreByName(genre).getId());
            int platformId = Math.toIntExact(platformRepository.findPlatformByName(platform).getId());
            model.addAttribute("games", gameRepository.findGamesByGenreAndPlatform(genreId,platformId));
            model.addAttribute("genre", genreRepository.findGenreByName(genre));
            model.addAttribute("platform", platformRepository.findPlatformByName(platform));
        } else if (genre.equals("default") && !year.equals("default") && !platform.equals("default")) {
            String begin = year + "0101";
            int beginYear = Integer.parseInt(begin);
            String end = year + "1224";
            int endYear = Integer.parseInt(end);
            int platformId = Math.toIntExact(platformRepository.findPlatformByName(platform).getId());
            model.addAttribute("games", gameRepository.findGamesByDateAndPlatform(beginYear, endYear, platformId));
            model.addAttribute("year", year);
            model.addAttribute("platform", platformRepository.findPlatformByName(platform));
        } else if (!genre.equals("default") && !year.equals("default") && !platform.equals("default")) {
            String begin = year + "0101";
            int beginYear = Integer.parseInt(begin);
            String end = year + "1224";
            int endYear = Integer.parseInt(end);
            int platformId = Math.toIntExact(platformRepository.findPlatformByName(platform).getId());
            int genreId = Math.toIntExact(genreRepository.findGenreByName(genre).getId());
            model.addAttribute("games", gameRepository.findGamesByGenreAndDateAndPlatform(genreId, beginYear, endYear, platformId));
            model.addAttribute("year", year);
            model.addAttribute("platform", platformRepository.findPlatformByName(platform));
            model.addAttribute("genre", genreRepository.findGenreByName(genre));
        }
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("platforms", platformRepository.findAll());
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
            user.getUser().setNumberOfRatedGames(ratingRepository.numberOfRatingsPerUser(user.getUser().getId()));
            userRepository.save(user.getUser());
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
        Game game = gameRepository.findById(rating.getGame().getId()).orElseThrow(EntityNotFoundException::new);
        game.setAvgRating(ratingRepository.getAvgRating(rating.getGame().getId()));
        gameRepository.save(game);
        return "redirect:/game-details/" + rating.getGame().getId();
    }
    @GetMapping("/super-admin/add-game")
    public String add(Model model) {
        model.addAttribute("game", new Game());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("platforms", platformRepository.findAll());
        return "game/add";
    }
    @PostMapping("/super-admin/add-game")
    public String save(@Valid Game game, BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("genres", genreRepository.findAll());
            model.addAttribute("publishers", publisherRepository.findAll());
            model.addAttribute("platforms", platformRepository.findAll());
            return "game/add";
        }
        gameRepository.save(game);
        return "redirect:/game-details/" + game.getId();
    }
    @GetMapping("/super-admin/edit-game/{id}")
    public String edit(Model model, @PathVariable long id) {
        model.addAttribute("game", gameRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("platforms", platformRepository.findAll());
        return "game/edit";
    }
    @PostMapping("/super-admin/update-game")
    public String update(@Valid Game game, BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("genres", genreRepository.findAll());
            model.addAttribute("publishers", publisherRepository.findAll());
            model.addAttribute("platforms", platformRepository.findAll());
            return "game/edit";
        }
        gameRepository.save(game);
        return "redirect:/game-details/" + game.getId();
    }
    @GetMapping("super-admin/delete-game-confirm/{id}")
    public String confirm(Model model, @PathVariable long id) {
        Game game = gameRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("gameId", game.getId());
        return "game/confirmDelete";
    }
    @GetMapping("super-admin/delete-game/{id}")
    @Transactional
    public String delete( @PathVariable long id, @AuthenticationPrincipal CurrentUser user) {
        Game game = gameRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (user.getUser().getId() == 1){
            List<Discussion> discussionList = discussionRepository.findDiscussionsByGame_Id(id);
            for (Discussion discussion : discussionList) {
                commentRepository.deleteAllByDiscussion_Id(discussion.getId());
                discussionRepository.delete(discussion);
            }
            ratingRepository.deleteAllByGame_Id(game.getId());
            gameRepository.delete(game);
            return "redirect:/game-list/";
        }
        return "redirect:/admin/books/all";
    }
}
