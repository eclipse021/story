package Homework.Fighting.config;

import lombok.extern.java.Log;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class StoryExceptionHandler {
   @ExceptionHandler(MethodArgumentNotValidException.class)
    protected BaseResponse<BaseResponseStatus> methodValidException(MethodArgumentNotValidException e){
       BindingResult bindingResult = e.getBindingResult();
       List<String> errorList = new ArrayList<>();

       for(FieldError fieldError : bindingResult.getFieldErrors()){
           errorList.add(fieldError.getDefaultMessage());
       }

       return new BaseResponse(BaseResponseStatus.INPUT_VALUE_INVALID, errorList);

   }

    /* @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ErrorResponse methodValidException(MethodArgumentNotValidException e){
        ErrorResponse errorResponse = makeErrorResponse(e.getBindingResult());
        return errorResponse;
    }

    private ErrorResponse makeErrorResponse(BindingResult bindingResult){
        Boolean isSuccess = null;
        Integer code = null;
        String message = null;

        if(bindingResult.hasErrors()){
            message = bindingResult.getFieldError().getDefaultMessage();

            String bindResultCode = bindingResult.getFieldError().getCode();

            switch (bindResultCode){
                case "NotBlank":
                    isSuccess = BaseResponseStatus.Request_Error.isSuccess();
                    code = BaseResponseStatus.Request_Error.getCode();
                break;

                case "Size":
                    isSuccess = BaseResponseStatus.Request_Error.isSuccess();
                    code = BaseResponseStatus.Request_Error.getCode();
                break;

                default:
                    isSuccess = BaseResponseStatus.Request_Error.isSuccess();
                    code = BaseResponseStatus.Request_Error.getCode();
            }
        }
        return new ErrorResponse(isSuccess, code, message);
    }*/


}

