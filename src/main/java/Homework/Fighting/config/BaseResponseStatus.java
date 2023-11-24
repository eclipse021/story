package Homework.Fighting.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    //요청 성공 시
    Success(true, 200, "ok"),
    //외적 문제
    No_privilge(false, 3000, "권한이 없습니다."),

    //User 계정 생성 관련 이슈
    User_no_exist(false, 4005, "존재하지 않는 유저입니다." ),
    User_nickname_duplicate(false, 4010, "해당 닉네임이 이미 존재합니다."),
    User_nickname_not(false, 4010, "닉네임을 입력해주세요."),
    User_nickname_over(false, 4010, "닉네임이 20자를 초과합니다."),
    User_selfintroduction_over(false, 4015, "자기소개를 255지 이내로 입력해주세요."),
    User_profile_problem(false, 4020, "프로필 형식이 잘못되었습니다."),

    //Blog 생성 관련 이슈
    Blog_name_already_exist(false, 4100, "블로그 이름이 이미 존재합니다."),
    Blog_no_exist(false, 4105, "블로그가 존재하지 않습니다.");
    private boolean isSuccess;
    private int code;
    private String message;

    BaseResponseStatus(boolean isSuccess, int code, String message){
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
