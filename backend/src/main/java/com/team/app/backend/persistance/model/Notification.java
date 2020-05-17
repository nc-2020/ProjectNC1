package com.team.app.backend.persistance.model;

import java.sql.Timestamp;
import java.util.Date;

public class Notification extends Message{

    private boolean seen;


    public Notification(Long id, String text, boolean seen, Timestamp date, Long categoryId, Long userId) {
        super( id,  text, date, categoryId, userId);
        this.seen = seen;
    }

    public Notification() {
        this.seen = false;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

}
