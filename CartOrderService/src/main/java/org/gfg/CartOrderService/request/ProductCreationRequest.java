package org.gfg.CartOrderService.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gfg.CartOrderService.model.Category;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreationRequest {

    private String productName;
    private int quantity;
    private double price;
    private String categoryName;
}
