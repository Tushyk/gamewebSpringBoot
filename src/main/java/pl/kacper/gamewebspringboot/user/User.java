package pl.kacper.gamewebspringboot.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Getter
@Setter
@Table( name = "users")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 60)
    @NotBlank(message = "pole nie moze byc puste")
    private String username;
    @NotBlank(message = "pole nie moze byc puste")
    private String password;
    private int enabled;
    @Column(name = "rated_games")
    private Long numberOfRatedGames;
    @Column(name = "created_discussions")
    private Long numberOfDiscussions;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;
}
