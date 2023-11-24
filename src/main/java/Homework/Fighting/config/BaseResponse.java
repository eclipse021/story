package Homework.Fighting.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private final boolean isSuccess;
    private final int code;
    private final String message;
    private final T data;

    //예외 처리
    public BaseResponse(BaseResponseStatus status){
        this.isSuccess = status.isSuccess();
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = null;
    }

    public BaseResponse(BaseResponseStatus status, Exception e){
        this.isSuccess = status.isSuccess();
        this.code = status.getCode();
        this.message = e.getMessage();
        this.data = null;
    }

    //요청이 성공적으로 됐을 때
    public BaseResponse(T data){
        this.isSuccess = BaseResponseStatus.Success.isSuccess();
        this.code = BaseResponseStatus.Success.getCode();
        this.message = BaseResponseStatus.Success.getMessage();
        this.data = data;
    }

    /*public BaseReponse(String errorMessage){

    }*/
}
