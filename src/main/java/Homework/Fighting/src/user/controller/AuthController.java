package Homework.Fighting.src.user.controller;


import Homework.Fighting.config.BaseResponse;
import Homework.Fighting.src.user.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Transactional
public class AuthController {

    private final AuthService authService;

    /*@PostMapping("/signup")
    public BaseResponse<String> signup()*/

}
