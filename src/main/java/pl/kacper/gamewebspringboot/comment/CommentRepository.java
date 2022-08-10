package pl.kacper.gamewebspringboot.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByDiscussion_IdOrderByCreatedOn(Long discussionId);
    List<Comment> findAllByDiscussion_Id(Long discussionId);
    void deleteAllByDiscussion_Id(Long discussionId);
}
