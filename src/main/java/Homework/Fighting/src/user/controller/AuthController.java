package Homework.Fighting.src.user.controller;


import Homework.Fighting.config.BaseException;
import Homework.Fighting.config.BaseResponse;
import Homework.Fighting.src.user.dto.LoginDto;
import Homework.Fighting.src.user.service.AuthService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Transactional
public class AuthController {

    private final AuthService authService;

    //회원가입 api
    @PostMapping("/signup")
    @ResponseBody
    public BaseResponse<String> signUp(@RequestBody @Valid LoginDto loginDto){
        try{
            authService.signUp(loginDto);
            return new BaseResponse<>("계정이 생성되었습니다.");
        }
        catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

}
