package com.team.app.backend.persistance.model;

import java.util.Date;

public class Announcement extends Message {
    private String title;
    private byte[] image;
    private Long statusId;

    public Announcement(){
       super();
    }

    public Announcement(Long id, String title, String text, Date date, byte[] image, Long statusId,Long categoryId, Long userId) {
        super( id,  text, date, categoryId, userId);
        this.title = title;
        this.image = image;
        this.statusId = statusId;

    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
