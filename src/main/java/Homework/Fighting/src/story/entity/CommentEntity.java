package Homework.Fighting.src.story.entity;

import Homework.Fighting.config.BaseEntity;
import Homework.Fighting.src.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor
@DynamicInsert
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(nullable = false, length = 100)
    private String contents;

    @Column(name = "group_number", nullable = false)
    private int groupNumber;

    @Column(name = "comment_level", nullable = false)
    private int commentLevel;

    @Column(name = "count_like", nullable = false)
    private int countLike;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;
}
