package org.gfg.OnboardingService.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gfg.Common.enums.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse extends Response{
    String mobNo;
    UserRole userRole;
}
