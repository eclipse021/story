package Homework.Fighting.src.story.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetPostDto {
    private String userNickname;

    private String blogName;

    private String postTitle;
    private LocalDateTime createdAt;
    private String content;
    private int postCountLike;

    private List<GetCommentDto> commentDtoList;

}
