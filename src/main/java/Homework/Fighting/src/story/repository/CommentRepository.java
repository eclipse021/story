package Homework.Fighting.src.story.repository;

import Homework.Fighting.config.Status;
import Homework.Fighting.src.story.entity.CommentEntity;
import Homework.Fighting.src.story.entity.PostEntity;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Registered
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query("select max(c.groupNumber) from CommentEntity c")
    Integer maxGroupNumber();

    List<CommentEntity> findCommentEntitiesByPostAndStatus(PostEntity post, Status status);

}
