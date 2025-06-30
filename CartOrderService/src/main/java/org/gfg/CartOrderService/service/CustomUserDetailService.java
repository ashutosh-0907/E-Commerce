package org.gfg.CartOrderService.service;

import org.gfg.Common.util.CommonConstants;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            String url = "http://localhost:9091/onboarding-service/find/user/" + username;
            String response = restTemplate.getForObject(url, String.class);
            if (response == null) {
                throw new UsernameNotFoundException("User not found");
            }
            JSONObject responseObject = new JSONObject(response);
            String password = responseObject.getString("password");
            String role = responseObject.getString("userRole");

            return User.builder()
                    .username(username)
                    .password(password)
                    .roles(role.toUpperCase())
                    .build();
        } catch (Exception e) {
            throw new UsernameNotFoundException("Login failed");
        }
    }
}

