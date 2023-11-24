package Homework.Fighting.config;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{
    private final BaseReponseStatus status;

    BaseException(BaseReponseStatus status){
        super(status.getMessage());
        this.status = status;
    }

}
