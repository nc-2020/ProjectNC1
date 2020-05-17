package com.team.app.backend.service;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailsService {


    public SimpleMailMessage activationLetter(String recipientAddress, String activate_link) {
        String subject = "Registration Confirmation";
        String confirmationUrl = "/api/user/activate?token=" + activate_link;
        String message = "Welcome on our site!" +
                "To continue press next link ";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("Brain-duel");
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " https://brainduel.herokuapp.com" + confirmationUrl);
        return email;
    }

    public SimpleMailMessage recoveryPasswordEmail(String recipientAddress, String activate_link) {
//        String subject = "Registration Confirmation";
//        String confirmationUrl = "/api/user/activate?token=" + activate_link;
//        String message = "Welcome on our site!" +
//                "To continue press next link ";

        SimpleMailMessage email = new SimpleMailMessage();
//        email.setFrom("Brain-duel");
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText(message + " https://brainduel.herokuapp.com" + confirmationUrl);
        return email;
    }
}
