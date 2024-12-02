package com.arrazyfathan.ecommerce.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    public Boolean status;
    public String message;
    public Object data;
}
