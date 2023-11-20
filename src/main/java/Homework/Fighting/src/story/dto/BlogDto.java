package Homework.Fighting.src.story.dto;

import Homework.Fighting.config.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BlogDto {
    @NotBlank(message = "블로그 이름을 입력해주세요.")
    @Size(max = 20, message = "이름을 20자 이내로 지어주세요.")
    private String name;

    @NotBlank(message = "블로그 소개글을 작성해주세요.")
    @Size(max = 100, message = "100자 이내로 작성해주세요.")
    private String introduction;

    private String profile;

}
