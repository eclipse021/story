package Homework.Fighting.src.story.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    @NotBlank(message = "댓글을 입력해주세요.")
    @Size(min = 1, max = 100, message = "100자 이내로 입력해주세요.")
    private String content;
}
