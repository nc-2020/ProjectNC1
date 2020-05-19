package com.team.app.backend.service;

import com.team.app.backend.persistance.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailsService {


    public SimpleMailMessage activationLetter(User user) {
        String subject = "Registration Confirmation";
        String confirmationUrl = "https://brainduel.herokuapp.com/api/user/activate?token=" + user.getActivate_link();
        String message = "Hi, " +user.getLastName()+" "+user.getFirstName()+"\n"+
                "Welcome on our site!\n"+
                "To confirm registration of your account, please press next link :\n"+
                confirmationUrl+
                "\nThank you and happy playing!\n"+
                "Good luck,\n"+"Your Brain-Duel Team";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("Brain-duel");
        email.setTo(user.getEmail());
        email.setSubject(subject);
        email.setText(message);
        return email;
    }

    public SimpleMailMessage recoveryPasswordEmail(User user,String password) {
        String subject = "New Password";
        String message = "Hi, " +user.getLastName()+" "+user.getFirstName()+"\n"+
                "You or someone else requested resetting a password for account "+user.getUsername()+
                "\nYour new password:\n"+
                password+
                "\nBest luck,\n"+"Your Brain-Duel Team";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("Brain-duel");
        email.setTo(user.getEmail());
        email.setSubject(subject);
        email.setText(message);
        return email;
    }
}
