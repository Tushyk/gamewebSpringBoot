package pl.kacper.gamewebspringboot.discussion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.kacper.gamewebspringboot.game.Game;
import pl.kacper.gamewebspringboot.user.User;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table( name = "discussions")
@ToString
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Topic must have a title")
    private String topic;
    @ManyToOne
    private User user;
    @ManyToOne
    private Game game;
}
