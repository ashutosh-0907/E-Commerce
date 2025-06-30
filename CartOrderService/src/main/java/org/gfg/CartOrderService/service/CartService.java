package org.gfg.CartOrderService.service;

import org.gfg.CartOrderService.model.Cart;
import org.gfg.CartOrderService.model.CartItem;
import org.gfg.CartOrderService.model.Product;
import org.gfg.CartOrderService.repository.CartItemRepo;
import org.gfg.CartOrderService.repository.ProductRepo;
import org.gfg.CartOrderService.response.CartResponse;
import org.gfg.CartOrderService.response.UserResponse;
import org.gfg.OnboardingService.model.User;
import org.gfg.CartOrderService.repository.CartRepo;
import org.gfg.OnboardingService.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CartItemRepo cartItemRepo;

    @Autowired
    RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String userServiceUrl;

    public ResponseEntity<?> addToCart(String mobNo, int productId, Integer quantity) {

        String url = userServiceUrl + "/find/user/" + mobNo;

        ResponseEntity<UserResponse> response;
        try {
            response = restTemplate.getForEntity(url, UserResponse.class);
        } catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        UserResponse user = response.getBody();
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        int id = user.getUserId();

        Optional<Product> product = productRepo.findByProductId(productId);
        if (product.isEmpty()) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        if (product.get().getQuantity() < quantity) {
            return new ResponseEntity<>("Insufficient Quantity", HttpStatus.BAD_REQUEST);
        }

        Cart cart = cartRepo.findById(id)
                .orElseGet(() -> {
                    Cart newCart = Cart.builder().userId(id).totalPrice(0.0).cartItemList(new ArrayList<>()).build();
                    return cartRepo.save(newCart);
                });

        Optional<CartItem> existingCartItem = cartItemRepo.findByUserIdAndProduct_ProductId(id, productId);
        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            int newQuantity = cartItem.getQuantity() + quantity;

            if (product.get().getQuantity() < newQuantity) {
                return new ResponseEntity<>("Can't add more items: Insufficient quantity", HttpStatus.BAD_REQUEST);
            }
            cartItem.setQuantity(newQuantity);
            cartItem.setCartItemPrice(newQuantity * product.get().getPrice());
            cartItemRepo.save(cartItem);

        } else {
            CartItem newCartItem = CartItem.builder().userId(id).product(product.get()).quantity(quantity).cartItemPrice(quantity * product.get().getPrice()).cart(cart).build();
            cartItemRepo.save(newCartItem);
            cart.getCartItemList().add(newCartItem);
        }
        double totalPrice = cart.getCartItemList().stream()
                .mapToDouble(CartItem::getCartItemPrice)
                .sum();
        cart.setTotalPrice(totalPrice);
        cartRepo.save(cart);

        return new ResponseEntity<>("Item added to cart successfully", HttpStatus.OK);
    }


    public ResponseEntity<?> findCart(String mobNo) {
        try {
            String url = userServiceUrl + "/find/user/" + mobNo;
            ResponseEntity<UserResponse> response = restTemplate.getForEntity(url, UserResponse.class);

            // Check if user exists
            if (response.getBody() == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            UserResponse user = response.getBody();
            int userId = user.getUserId();

            // Find cart by mobile number
            Optional<?> cartOptional = cartRepo.findByUserId(userId);

            if (cartOptional.isPresent()) {
                return new ResponseEntity<>(cartOptional.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cart not found for mobile number: " + userId, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while finding cart: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


