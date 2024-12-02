package com.arrazyfathan.ecommerce.controller;

import com.arrazyfathan.ecommerce.data.entity.ProductEntity;
import com.arrazyfathan.ecommerce.data.response.BaseResponsePagination;
import com.arrazyfathan.ecommerce.data.response.PagingInfo;
import com.arrazyfathan.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    BaseResponsePagination getProducts(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Integer categoryId
    ) {
        String key = (q == null ? "NQ" : "Q") + (categoryId == null ? "NC" : "C");

        PagingInfo<ProductEntity> productsPage = switch (key) {
            case "NQC" -> productService.getProducts(page, size, categoryId);
            case "QNC" -> productService.getProducts(page, size, q);
            case "QC" -> productService.getProducts(page, size, q, categoryId);
            default -> productService.getProducts(page, size);
        };

        BaseResponsePagination response = new BaseResponsePagination();
        response.setMessage("Success");
        response.setStatus(true);
        response.setData(productsPage);
        return response;
    }

}
