package org.gfg.NotificationService.consumer;

import org.gfg.NotificationService.worker.EmailWorker;
import org.gfg.Common.enums.UserIdentifier;
import org.gfg.Common.util.CommonConstants;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {

    @Autowired
    EmailWorker emailWorker;

    @KafkaListener(topics = "USER_CREATION_QUEUE", groupId = "email_notification")
    public void listenNewlyCreatedConfig(String data){
        System.out.println(data);
        JSONObject jsonObject = new JSONObject(data);
        String name = jsonObject.getString(CommonConstants.USER_NAME);
        String email = jsonObject.getString(CommonConstants.USER_EMAIL);

        emailWorker.sendEmail(name,email);
        System.out.println("email has sent");
    }
}
