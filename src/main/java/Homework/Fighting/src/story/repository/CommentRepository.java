package Homework.Fighting.src.story.repository;

import Homework.Fighting.src.story.entity.CommentEntity;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Registered
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query("select max(c.groupNumber) from CommentEntity c")
    Integer maxGroupNumber();


}
