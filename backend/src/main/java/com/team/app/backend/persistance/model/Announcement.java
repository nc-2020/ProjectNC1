package com.team.app.backend.persistance.model;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Announcement extends Message {
    private String title;
    private String image;
    private Long statusId;

    public Announcement(Long id, String title, String text, Timestamp date, String image, Long statusId, Long categoryId, Long userId) {
        super( id,  text, date, categoryId, userId);
        this.title = title;
        this.image = image;
        this.statusId = statusId;
    }
}
