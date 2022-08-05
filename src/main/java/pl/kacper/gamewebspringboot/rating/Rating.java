package pl.kacper.gamewebspringboot.rating;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.kacper.gamewebspringboot.game.Game;
import pl.kacper.gamewebspringboot.user.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@Table( name = "ratings")
@ToString
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Game game;
    @Min(1)
    @Max(10)
    private double rating;
}
