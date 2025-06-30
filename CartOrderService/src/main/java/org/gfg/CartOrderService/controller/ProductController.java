package org.gfg.CartOrderService.controller;

import org.gfg.CartOrderService.model.Product;
import org.gfg.CartOrderService.request.ProductCreationRequest;
import org.gfg.CartOrderService.response.ProductResponse;
import org.gfg.CartOrderService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/save/products")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductCreationRequest productCreationRequest){

        ProductResponse productResponse = new ProductResponse();
        Product product = productService.addProduct(productCreationRequest);
        productResponse.setCode("001");
        productResponse.setMsg("product is added successfully");
        productResponse.setProductName(product.getProductName());
        productResponse.setCategory(product.getCategory().getCategoryName());
        productResponse.setQuantity(product.getQuantity());

        return  ResponseEntity.ok(productResponse);
    }


}
