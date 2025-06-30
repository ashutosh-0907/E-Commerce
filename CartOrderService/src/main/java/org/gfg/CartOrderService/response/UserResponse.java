package org.gfg.CartOrderService.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int userId;
    private String name;
    private String email;
    private String mobNo;
    private String password;
    private String userRole;
}
