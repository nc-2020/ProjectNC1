package com.team.app.backend.persistance.model;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private byte[] image;
    private Date registr_date;
    private String activate_link;
    private UserStatus status;
    private Role role;

    public User(Long id, String firstname, String lastname, String username, String password, String email, byte[] image, Date registr_date, String activate_link, UserStatus status, Role role) {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.image = image;
        this.registr_date = registr_date;
        this.activate_link = activate_link;
        this.status = status;
        this.role = role;
    }
}
