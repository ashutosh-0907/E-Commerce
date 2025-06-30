package org.gfg.OnboardingService.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gfg.Common.enums.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindUserRequest {
    String mobileNo;
    String password;
    UserRole userRole;


}
