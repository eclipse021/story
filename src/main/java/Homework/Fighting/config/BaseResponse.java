package Homework.Fighting.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private final boolean isSuccess;
    private final int code;
    private final String message;
    //필수가 아닌 값에 final 빼기
    private T data;
    private List<String> errorList;

    //예외 처리
    public BaseResponse(BaseResponseStatus status){
        this.isSuccess = status.isSuccess();
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public BaseResponse(BaseResponseStatus status, Exception e){
        this.isSuccess = status.isSuccess();
        this.code = status.getCode();
        this.message = e.getMessage();
        this.data = null;
    }

    public BaseResponse(BaseResponseStatus status, List<String> errorList ){
        this.isSuccess = status.isSuccess();
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = null;
        this.errorList = errorList;
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
