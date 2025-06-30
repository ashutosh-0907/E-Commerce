package org.gfg.CartOrderService.service;

import org.gfg.CartOrderService.model.Category;
import org.gfg.CartOrderService.model.Product;
import org.gfg.CartOrderService.repository.CategoryRepo;
import org.gfg.CartOrderService.repository.ProductRepo;
import org.gfg.CartOrderService.request.ProductCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CategoryRepo categoryRepo;

    public Product addProduct(ProductCreationRequest productCreationRequest){

        Optional<Product> existingProducts = productRepo.findByProductName(productCreationRequest.getProductName());
        if(existingProducts.isPresent()){
            Product existingProduct = existingProducts.get();
            int currentQuantity = existingProduct.getQuantity();
            int newQuantity = (productCreationRequest.getQuantity());
            int totalQuantity = currentQuantity + newQuantity;
            existingProduct.setQuantity(totalQuantity);
            existingProduct.setPrice(productCreationRequest.getPrice());
            return productRepo.save(existingProduct);
        }
        Product product = new Product();
        product.setProductName(productCreationRequest.getProductName());
        product.setPrice(productCreationRequest.getPrice());
        product.setQuantity(productCreationRequest.getQuantity());
        Category category = categoryRepo.findByCategoryName(productCreationRequest.getCategoryName())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setCategoryName(productCreationRequest.getCategoryName());
                    return categoryRepo.save(newCategory);
                });
        product.setCategory(category);

        try{
            Product dbProduct = productRepo.save(product);
            System.out.println("product is" + dbProduct);
            return dbProduct;
        } catch (Exception e) {
            System.out.println("exception"+e);
        }
        return null;
    }




}
