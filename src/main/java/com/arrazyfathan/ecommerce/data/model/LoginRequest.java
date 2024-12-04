package com.arrazyfathan.ecommerce.data.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LoginRequest {

    @NotBlank(message = "Phone number is required!")
    private String phoneNumber;
    
    @NotBlank(message = "Password is required!")
    private String password;
}
