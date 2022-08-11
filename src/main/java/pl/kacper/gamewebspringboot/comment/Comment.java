package pl.kacper.gamewebspringboot.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.kacper.gamewebspringboot.discussion.Discussion;
import pl.kacper.gamewebspringboot.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table( name = "comments")
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "message cant be blank")
    private String text;
    @Column(name = "created")
    private LocalDateTime createdOn;
    @Column(name = "updated")
    private LocalDateTime updatedOn;
    @ManyToOne
    private User user;
    @ManyToOne
    private Discussion discussion;
}
