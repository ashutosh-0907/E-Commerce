package org.gfg.CartOrderService.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse extends Response{
    String productName;
    String category;
    int quantity;
}
