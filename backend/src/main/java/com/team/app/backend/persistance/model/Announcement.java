package com.team.app.backend.persistance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Announcement extends Message {
    private String title;
    private byte[] image;
    private Long statusId;

    public Announcement(Long id, String title, String text, Date date, byte[] image, Long statusId,Long categoryId, Long userId) {
        super( id,  text, date, categoryId, userId);
        this.title = title;
        this.image = image;
        this.statusId = statusId;
    }
}
