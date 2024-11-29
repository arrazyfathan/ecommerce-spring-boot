package com.arrazyfathan.ecommerce;

import lombok.Data;

@Data
public class BaseResponsePagination {
    public Boolean status;
    public String message;
    public Object data;
    public Object pagination;
}