package Homework.Fighting.src.story.entity;

import Homework.Fighting.config.BaseEntity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@NoArgsConstructor
public class PostEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 255)
    private String contents;

    @Column(name = "count_like", nullable = false)
    private int countLike;

    @Column(name = "count_comment", nullable = false)
    private int countComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    private BlogEntity blog;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<CommentEntity> commentList = new ArrayList<CommentEntity>();
}
