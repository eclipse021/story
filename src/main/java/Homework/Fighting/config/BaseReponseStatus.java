package Homework.Fighting.config;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum BaseReponseStatus {

    //요청 성공 시
    Success(true, 200, "ok"),
    //User 계정 생성 관련 이슈
    User_already_exist(false, 4005, "유저가 이미 존재합니다." ),
    User_nickname_duplicate(false, 4010, "해당 닉네임이 이미 존재합니다."),
    User_nickname_not(false, 4010, "닉네임을 입력해주세요."),
    User_nickname_over(false, 4010, "닉네임이 20자를 초과합니다."),
    User_selfintroduction_over(false, 4015, "자기소개를 255지 이내로 입력해주세요."),
    User_profile_problem(false, 4020, "프로필 형식이 잘못되었습니다.");

    private boolean isSuccess;
    private int code;
    private String message;

    BaseReponseStatus(boolean isSuccess, int code, String message){
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
