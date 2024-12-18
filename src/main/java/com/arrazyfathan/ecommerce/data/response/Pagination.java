package com.arrazyfathan.ecommerce.data.response;

import lombok.Data;

@Data
public class Pagination {
    public Integer currentPage;
    public Integer totalPage;
    public Integer pageSize;
    public Long totalItems;
}