package pl.kacper.gamewebspringboot.discussion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    List<Discussion> findDiscussionsByGame_Id(Long gameId);
    List<Discussion> findDiscussionsByUser_Id(Long userId);
}
