package Homework.Fighting.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseReponse<T> {
    private final boolean isSuccess;
    private final int code;
    private final String message;
    private final T data;

    public BaseReponse(BaseReponseStatus status){
        this.isSuccess = status.isSuccess();
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = null;
    }

    //요청이 성공적으로 됐을 때
    public BaseReponse(T data){
        this.isSuccess = BaseReponseStatus.Success.isSuccess();
        this.code = BaseReponseStatus.Success.getCode();
        this.message = BaseReponseStatus.Success.getMessage();
        this.data = data;
    }

    /*public BaseReponse(String errorMessage){

    }*/
}
