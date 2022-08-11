package pl.kacper.gamewebspringboot.game;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import pl.kacper.gamewebspringboot.genre.Genre;
import pl.kacper.gamewebspringboot.platform.Platform;
import pl.kacper.gamewebspringboot.publisher.Publisher;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table( name = "games")
@ToString
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "field cant be blank")
    private String title;
    @ManyToOne
    private Publisher publisher;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    @ManyToOne
    private Genre genre;
    @ManyToMany
    private List<Platform> platforms = new ArrayList<>();
    private Double avgRating;
    private Long numberOfRatings;

}
