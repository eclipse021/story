package Homework.Fighting.src.story.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GetCommentDto {

    private String userNickname;
    private String userProfile;

    private String commentContent;
    private int commentCountLike;
    private LocalDateTime createdAt;

}
