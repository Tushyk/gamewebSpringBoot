package pl.kacper.gamewebspringboot.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findRatingsByUser_Id(Long userId);
    Rating findFirstByGame_IdAndUser_Id(long gameId, long userId);
    Rating findRatingByUser_IdAndAndGame_Id(long userId, long gameId);
    @Query("select Avg(r.rating) from Rating r where r.game.id = :id")
    Double getAvgRating(@Param("id") Long gameId);
    @Query("select r from Rating r order by r.rating desc")
    List<Rating> findRatingsOrdered();
    @Query("select count(r.rating) from Rating r where r.game.id = :id")
    Long numberOfRatingsPerGame(@Param("id") Long gameId);
    @Query("select count(r.rating) from Rating r where r.user.id = :id")
    Long numberOfRatingsPerUser(@Param("id") Long userId);
    void deleteAllByGame_Id(long gameId);
}
