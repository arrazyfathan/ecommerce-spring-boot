package com.arrazyfathan.ecommerce;

import com.arrazyfathan.ecommerce.entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    BaseResponsePagination getProducts(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String q
    ) {


        Page<ProductEntity> productsPage;
        List<ProductEntity> producstList;

        if (q == null) {
            productsPage = productService.getProducts(page, size);
        } else  {
            productsPage = productService.getProducts(page, size, q);
        }

        producstList = productsPage.getContent();

        Pagination pagination = new Pagination();
        pagination.setCurrentPage(page);
        pagination.setTotalPage(productsPage.getTotalPages());
        pagination.setPageSize(size);
        pagination.setTotalItems(productsPage.getTotalElements());

        BaseResponsePagination response = new BaseResponsePagination();
        response.setMessage("Success");
        response.setStatus(true);
        response.setData(producstList);
        response.setPagination(pagination);
        return response;
    }

}
