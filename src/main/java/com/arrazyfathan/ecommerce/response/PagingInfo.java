package com.arrazyfathan.ecommerce.response;


import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PagingInfo<T> {
    public Integer currentPage;
    public Integer nextPage;
    public Integer prevPage;
    public Integer totalPages;
    public Long totalItems;
    public List<T> content;

    public static <T> PagingInfo<T> convertFromPage(Page<T> page) {
        PagingInfo<T> pagingInfo = new PagingInfo<>();

        pagingInfo.currentPage = page.getNumber() + 1;
        pagingInfo.totalPages = page.getTotalPages();
        pagingInfo.totalItems = page.getTotalElements();
        pagingInfo.content = page.getContent();

        if (pagingInfo.currentPage > pagingInfo.totalPages) {

        }

        if (page.hasNext()) {
            pagingInfo.nextPage = (page.getNumber() + 1) + 1;
        }

        if (page.hasPrevious()) {
            if (pagingInfo.currentPage > pagingInfo.totalPages) {
                pagingInfo.prevPage = pagingInfo.totalPages;
            } else  {
                pagingInfo.prevPage = pagingInfo.currentPage - 1;
            }
        }



        return pagingInfo;
    }
}
