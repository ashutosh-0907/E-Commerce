package org.gfg.CartOrderService.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartCreationRequest {
    private int totalPrice = 0;
    private List<ProductCreationRequest> products = new ArrayList<>();
}
