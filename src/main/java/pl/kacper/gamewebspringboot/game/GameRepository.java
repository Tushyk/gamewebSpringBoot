package pl.kacper.gamewebspringboot.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kacper.gamewebspringboot.rating.Rating;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game findGameByTitleIgnoreCase(String title);
    @Query("select g from Game g order by g.avgRating desc")
    List<Game> findGamesOrdered();
    //--------------------------------------------------FORMULARZ-------------------------------------------------------
    @Query(value = "select * from games join games_platforms gp on games.id = gp.game_id where games.genre_id= ?1 and games.release_date between ?2 and ?3 and gp.platforms_id=?4", nativeQuery = true)
    List<Game> findGamesByGenreAndDateAndPlatform(int genreId, int beginYear, int endYear, int platformId);
    @Query(value = "select * from games join games_platforms gp on games.id = gp.game_id where games.genre_id= ?1", nativeQuery = true)
    List<Game> findGamesByGenre(int genreId);
    @Query(value = "select * from games join games_platforms gp on games.id = gp.game_id where games.release_date between ?1 and ?2", nativeQuery = true)
    List<Game> findGamesByDate(int beginYear, int endYear);
    @Query(value = "select * from games join games_platforms gp on games.id = gp.game_id where gp.platforms_id=?1", nativeQuery = true)
    List<Game> findGamesByPlatform(int platformId);
    @Query(value = "select * from games join games_platforms gp on games.id = gp.game_id where games.genre_id= ?1 and games.release_date between ?2 and ?3", nativeQuery = true)
    List<Game> findGamesByGenreAndDate(int genreId, int beginYear, int endYear);
    @Query(value = "select * from games join games_platforms gp on games.id = gp.game_id where games.genre_id= ?1 and gp.platforms_id=?2", nativeQuery = true)
    List<Game> findGamesByGenreAndPlatform(int genreId, int platformId);
    @Query(value = "select * from games join games_platforms gp on games.id = gp.game_id where games.release_date between ?1 and ?2 and gp.platforms_id=?3", nativeQuery = true)
    List<Game> findGamesByDateAndPlatform(int beginYear, int endYear, int platformId);
    //----------------------------------------------FORMULARZ-----------------------------------------------------------
}
