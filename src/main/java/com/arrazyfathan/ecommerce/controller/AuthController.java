package com.arrazyfathan.ecommerce.controller;


import com.arrazyfathan.ecommerce.data.model.LoginRequest;
import com.arrazyfathan.ecommerce.data.model.RegisterRequest;
import com.arrazyfathan.ecommerce.data.response.BaseResponse;
import com.arrazyfathan.ecommerce.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public BaseResponse postRegister(
            @RequestBody RegisterRequest registerRequest
    ) {
        Boolean result = authService.register(registerRequest);
        return new BaseResponse(result, "Success", result);
    }

    @PostMapping("/login")
    public BaseResponse postLogin(
            @RequestBody LoginRequest loginRequest
    ) {
        String token = authService.login(loginRequest);
        return new BaseResponse(true, "Success", token);
    }

}
