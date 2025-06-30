package org.gfg.OnboardingService.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.gfg.Common.enums.UserRole;

@Data
@NoArgsConstructor
public class FindUserResponse extends Response{
    String mobileNo;
    String password;
    UserRole userRole;

    public FindUserResponse(String mobileNo, String password, UserRole userRole) {
        this.mobileNo = mobileNo;
        this.password = password;
        this.userRole = userRole;
    }
}
