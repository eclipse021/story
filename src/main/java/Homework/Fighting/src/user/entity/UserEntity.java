package Homework.Fighting.src.user.entity;

import Homework.Fighting.config.BaseEntity;
import Homework.Fighting.src.story.entity.BlogEntity;
import Homework.Fighting.src.story.entity.CommentEntity;
import Homework.Fighting.src.story.entity.PostEntity;
import Homework.Fighting.src.user.dto.LoginDto;
import Homework.Fighting.src.user.dto.UserDto;
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

    //아이디 조건 추가하기
    @Column(nullable = false, length = 20)
    private String username;

    //비밀번호 조건 추가하기
    @Column(nullable = false)
    private String password;

    //이메일 조건 추가하기
    @Column
    private String email;

    @Column(nullable = false, length = 8)
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

    @Column
    private String role;

    public UserEntity(UserDto userDto){
        this.nickname = userDto.getNickname();
        this.selfInformation = userDto.getSelfInformation();
        this.profile = userDto.getProfile();
    }

    public UserEntity(LoginDto loginDto, String encPassword){
        this.username = loginDto.getUsername();
        this.password = encPassword;
        this.email = loginDto.getEmail();
        this.nickname = loginDto.getNickname();
    }

    public void updateUser(UserDto userDto){
        this.nickname = userDto.getNickname();
        this.selfInformation = userDto.getSelfInformation();
        this.profile = userDto.getProfile();
    }

    public void setRole(String role){
        this.role = role;
    }
}
