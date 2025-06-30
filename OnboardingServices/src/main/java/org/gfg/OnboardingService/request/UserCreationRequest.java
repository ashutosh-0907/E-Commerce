package org.gfg.OnboardingService.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gfg.Common.enums.UserRole;
import org.gfg.OnboardingService.model.Address;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationRequest {
    private String name;
    private String email;
    private String mobNo;
    private String password;
    private Address address;
    private UserRole userRole;
}
