package org.gfg.NotificationService.worker;

import jakarta.mail.internet.MimeMessage;
import org.gfg.NotificationService.utility.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailWorker {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(String name, String email){

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            System.out.println("inside the send email notification method");
            MimeMessageHelper mail = new MimeMessageHelper(mimeMessage, true);
            String emailContent = EmailTemplate.EMAIL_TEMPLATE;
            emailContent = emailContent.replaceAll("==name==",name);
            mail.setText(emailContent,true);
            mail.setTo(email);
            mail.setFrom("aashutoshrai0907@gmail.com");
            mail.setSubject("Welcome to JBDL E Commerce Application");
            javaMailSender.send(mimeMessage);
            System.out.println("Email has been sent to User");
        }catch (MailAuthenticationException e) {
            System.err.println("Mail auth failed: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("General email error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
