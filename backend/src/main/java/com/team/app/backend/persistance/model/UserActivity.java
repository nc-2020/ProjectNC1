package com.team.app.backend.persistance.model;

import java.sql.Date;
import java.sql.Timestamp;

public class UserActivity extends Message{
    public UserActivity(Long id, String text, Timestamp date, Long categoryId, Long userId) {
        super( id,  text, date, categoryId, userId);
    }

    public UserActivity() {
    }
}
