package pl.kacper.gamewebspringboot.rating;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating findFirstByGame_IdAndUser_Id(long gameId, long userId);
}
