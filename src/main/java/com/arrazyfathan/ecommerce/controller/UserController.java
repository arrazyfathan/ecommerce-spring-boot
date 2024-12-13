package com.arrazyfathan.ecommerce.controller;


import com.arrazyfathan.ecommerce.data.entity.UserEntity;
import com.arrazyfathan.ecommerce.data.model.RegisterRequest;
import com.arrazyfathan.ecommerce.data.response.BaseResponse;
import com.arrazyfathan.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public BaseResponse getUser() {
        UserEntity userEntity = userService.userEntity();
        return new BaseResponse(true, "Success", userEntity);
    }

}
