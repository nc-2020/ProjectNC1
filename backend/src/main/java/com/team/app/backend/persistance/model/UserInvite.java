package com.team.app.backend.persistance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserInvite {
    private Long id;
    private boolean activated;
    private String inviteText;
    private Date inviteDate;
    private Long userIdFrom;
    private Long userIdTo;
    private String usernameFrom;

    public UserInvite(Long id, boolean activated, String inviteText, Date inviteDate, Long userIdFrom, Long userIdTo, String usernameFrom) {
        this.id = id;
        this.activated = activated;
        this.inviteText = inviteText;
        this.inviteDate = inviteDate;
        this.userIdFrom = userIdFrom;
        this.userIdTo = userIdTo;
        this.usernameFrom = usernameFrom;
    }
}
