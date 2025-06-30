package org.gfg.CartOrderService.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {
    private String mobNo;
    private int productId;
    private int quantity;
}
