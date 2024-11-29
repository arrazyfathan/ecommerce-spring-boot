package com.arrazyfathan.ecommerce;

import com.arrazyfathan.ecommerce.entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    BaseResponse getProduct() {
        List<ProductEntity> productEntityList = productService.getTopThree();
        BaseResponse response = new BaseResponse();
        response.setMessage("Success");
        response.setStatus(true);
        response.setData(productEntityList);
        return response;
    }

}
