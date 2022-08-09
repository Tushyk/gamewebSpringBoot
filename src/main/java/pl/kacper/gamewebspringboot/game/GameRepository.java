package pl.kacper.gamewebspringboot.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kacper.gamewebspringboot.rating.Rating;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game findGameByTitleIgnoreCase(String title);

    @Query("select g from Game g order by g.avgRating desc")
    List<Game> findGamesOrdered();
}
