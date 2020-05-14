package com.team.app.backend.persistance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Option extends OptionObj {

    private Boolean is_correct;
    private String text;
    private byte[] image;

    public Option(Boolean is_correct, String text, byte[] image) {
        this.is_correct = is_correct;
        this.text = text;
        this.image = image;
    }
}
