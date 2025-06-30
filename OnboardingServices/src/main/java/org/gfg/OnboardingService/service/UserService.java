package org.gfg.OnboardingService.service;

import org.gfg.Common.util.CommonConstants;
import org.gfg.OnboardingService.model.User;
import org.gfg.OnboardingService.repository.UserRepo;
import org.gfg.OnboardingService.request.UserCreationRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;


    public User saveUserDetails(UserCreationRequest userCreationRequest){

        JSONObject jsonObject = new JSONObject();

        User user = new User();
        user.setName(userCreationRequest.getName());
        user.setEmail(userCreationRequest.getEmail());
        user.setMobNo(userCreationRequest.getMobNo());
        user.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
        user.setAddress(userCreationRequest.getAddress());
        user.setUserRole(userCreationRequest.getUserRole());

        jsonObject.put(CommonConstants.USER_NAME, user.getName());
        jsonObject.put(CommonConstants.USER_EMAIL, user.getEmail());
        jsonObject.put(CommonConstants.USER_MOBILE, user.getMobNo());
        jsonObject.put(CommonConstants.USER_ROLE,user.getUserRole());


        try {
            User dbUser = userRepo.save(user);
            jsonObject.put(CommonConstants.USER_ID,user.getId());
            Thread thread = new Thread(()->{  // It will create a new thread and push data in kafka
                kafkaTemplate.send(CommonConstants.USER_CREATION_QUEUE_TOPIC,jsonObject.toString());
                System.out.println("data sent to kafka");
            });
            thread.start();
            return dbUser;
        } catch (Exception e) {
            System.out.println("exception "+ e);
        }
        return null;
    }


    public User findUserByMobNo(String mobNo) throws UsernameNotFoundException {
        return userRepo.findByMobNo(mobNo)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }



}
