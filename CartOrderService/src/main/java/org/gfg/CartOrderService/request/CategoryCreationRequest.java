package org.gfg.CartOrderService.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreationRequest {

    private String categoryName;
	private List<ProductCreationRequest> product = new ArrayList<>();
}
