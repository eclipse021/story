package Homework.Fighting.src.story.repository;

import Homework.Fighting.config.Status;
import Homework.Fighting.src.story.entity.PostEntity;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findByPostIdAndStatus(Long postId, Status status);
}
