package org.gfg.OnboardingService.controller;

import org.gfg.Common.enums.UserRole;
import org.gfg.OnboardingService.model.User;
import org.gfg.OnboardingService.repository.UserRepo;
import org.gfg.OnboardingService.request.LoginRequest;
import org.gfg.OnboardingService.request.UserCreationRequest;
import org.gfg.OnboardingService.response.FindUserResponse;
import org.gfg.OnboardingService.response.UserCreationResponse;
import org.gfg.OnboardingService.response.UserResponse;
import org.gfg.OnboardingService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/onboarding-service")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepo userRepo;



    UserCreationResponse userCreationResponse = new UserCreationResponse();


    @PostMapping("/create/user")
    public ResponseEntity<UserCreationResponse> saveUser(@RequestBody UserCreationRequest userCreationRequest) {

        User user = userService.saveUserDetails(userCreationRequest);


        userCreationResponse.setCode("002");
        userCreationResponse.setMessage("User is created");
        userCreationResponse.setName(userCreationRequest.getName());
        userCreationResponse.setEmail(userCreationRequest.getEmail());

        if (user == null) {
            userCreationResponse.setMessage("User is not created");
            userCreationResponse.setCode("02");
        }

        return new ResponseEntity<>(userCreationResponse , HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {

        System.out.println("Mobile: " + loginRequest.getMobileNo());
        System.out.println("Raw Password: " + loginRequest.getPassword());

        Optional<User> userOptional = userRepo.findByMobNo(loginRequest.getMobileNo());

        if (userOptional.isEmpty() ||
                !passwordEncoder.matches(loginRequest.getPassword(), userOptional.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("code", "0002", "message", "Invalid mobile number or password"));
        }

        User user = userOptional.get();
        UserRole role = user.getUserRole();

        System.out.println("Password from DB (hashed): " + user.getPassword());
        System.out.println("Password match: " + passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()));

        return ResponseEntity.ok(
                Map.of("code", "0001", "message", "Login successful", "role", role.name())
        );
    }

    @GetMapping("/find/user/{mobNo}")
    public ResponseEntity<?> getUserByMobile(@PathVariable String mobNo) {

        Optional<User> userOptional = userRepo.findByMobNo(mobNo);

        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }

        User user = userOptional.get();

        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getMobNo(),
                user.getPassword(),
                user.getUserRole().name()
        );
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }
}
