package Homework.Fighting.src.story.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "이름을 입력해주세요")
    @Size(min = 1, max = 20, message = "닉네임을 20자 이내로 입력해주세요")
    String nickname;

    @Size(max = 255, message = "자기소개를 255자 이내로 입력해주세요")
    String selfInformation;

    @Size(max = 255)
    String profile;
}
