package Homework.Fighting.src.story.repository;

import Homework.Fighting.config.Status;
import Homework.Fighting.src.story.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
    public Optional<BlogEntity> findBlogEntityByBlogIdAndStatus(Long id, Status status);
    public boolean existsByName(String name);

}
