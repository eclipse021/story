package Homework.Fighting.src.story.entity;

import Homework.Fighting.config.BaseEntity;
import Homework.Fighting.config.Status;
import Homework.Fighting.src.story.dto.BlogDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "blog ")
@NoArgsConstructor
@DynamicInsert
public class BlogEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long blogId;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 100)
    private String introduction;

    @Column(length = 255)
    private String profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    List<PostEntity> postList = new ArrayList<PostEntity>();

    public BlogEntity(BlogDto blogDto, UserEntity user){
        this.name = blogDto.getName();
        this.introduction = blogDto.getIntroduction();
        this.profile = blogDto.getProfile();
    }

    public void setStatus(Status status){this.status = status;}

    public void updateBlog(BlogDto blogDto){
        this.name = blogDto.getName();
        this.introduction = blogDto.getIntroduction();
        this.profile = blogDto.getProfile();
    }
}
