package org.gfg.CartOrderService.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse  extends Response{
    int quantity;
    double price;
}
