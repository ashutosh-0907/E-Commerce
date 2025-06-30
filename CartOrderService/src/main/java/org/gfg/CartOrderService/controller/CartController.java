package org.gfg.CartOrderService.controller;

import org.gfg.CartOrderService.request.CartItemRequest;
import org.gfg.CartOrderService.response.CartResponse;
import org.gfg.CartOrderService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart-service")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody CartItemRequest cartItemRequest){
        return cartService.addToCart(
                cartItemRequest.getMobNo(),
                cartItemRequest.getProductId(),
                cartItemRequest.getQuantity()
        );
    }
    @GetMapping("/find/cart/{mobNo}")
    public ResponseEntity<?> findCart(@PathVariable String mobNo) {
        try {
            // Call the service method to find the cart
            return cartService.findCart(mobNo);

        } catch (Exception e) {
            // Handle any unexpected errors
            CartResponse errorResponse = new CartResponse();
            errorResponse.setCode("500");
            errorResponse.setMsg("Error occurred while fetching cart: " + e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
