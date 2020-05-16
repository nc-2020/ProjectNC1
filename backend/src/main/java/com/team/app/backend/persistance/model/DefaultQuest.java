package com.team.app.backend.persistance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DefaultQuest extends OptionObj  {

    private String answer;
    private byte[] image;

    public DefaultQuest(String answer, byte[] image) {
        this.answer = answer;
        this.image = image;
    }
}
