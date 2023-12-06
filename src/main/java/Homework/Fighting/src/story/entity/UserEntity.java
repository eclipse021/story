package Homework.Fighting.src.story.entity;

import Homework.Fighting.config.BaseEntity;
import Homework.Fighting.src.story.dto.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor
@DynamicInsert
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(name = "self_information", length = 255)
    private String selfInformation;

    @Column(length = 255)
    private String profile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<BlogEntity> blogList = new ArrayList<BlogEntity>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<PostEntity> postList = new ArrayList<PostEntity>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<CommentEntity> commentList = new ArrayList<CommentEntity>();

    public UserEntity(UserDto userDto){
        this.nickname = userDto.getNickname();
        this.selfInformation = userDto.getSelfInformation();
        this.profile = userDto.getProfile();
    }

    public void updateUser(UserDto userDto){
        this.nickname = userDto.getNickname();
        this.selfInformation = userDto.getSelfInformation();
        this.profile = userDto.getProfile();
    }
}
