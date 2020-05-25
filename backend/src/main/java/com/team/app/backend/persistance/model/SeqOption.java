package com.team.app.backend.persistance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SeqOption extends OptionObj {
    private Integer serial_num;
    private String text;
    private String image;

    public SeqOption(Long id, Integer serial_num, String text, String image, Long quest_id) {
        super(id, quest_id);
        this.serial_num = serial_num;
        this.text = text;
        this.image = image;
    }
}
