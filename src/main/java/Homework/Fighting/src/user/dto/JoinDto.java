package Homework.Fighting.src.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class JoinDto {
    //아이디 조건 추가하기
    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 5, max = 20, message = "아이디는 5자 이상이여야 합니다.")
    private String username;

    //비밀번호 조건 추가하기
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 4, max = 20, message = "비밀번호는 4자 이상이어야 합니다.")
    private String password;

    //이메일 조건 추가하기
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(max = 8)
    private String nickname;

    @Size(max = 255)
    private String selfInformation;

    @Size(max = 255)
    private String profile;
}
