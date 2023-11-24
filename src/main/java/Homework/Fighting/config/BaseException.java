package Homework.Fighting.config;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{
    //예상한 예외를 따로 잡아주어야 하므로 status 필드 추가
    private final BaseResponseStatus status;

    public BaseException(BaseResponseStatus status){
        super(status.getMessage());
        this.status = status;
    }

}
