package com.arrazyfathan.ecommerce.response;

import lombok.Data;

@Data
public class BaseResponsePagination {
    public Boolean status;
    public String message;
    public Object data;
}