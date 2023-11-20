package Homework.Fighting.src.story.repository;


import Homework.Fighting.config.Status;
import Homework.Fighting.src.story.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByUserIdAndStatus(Long userId, Status status);

}
