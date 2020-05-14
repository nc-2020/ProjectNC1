package com.team.app.backend.persistance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerToDef {

    private Long userAnsw_id;
    private Long def_id;
    private String text;

    public AnswerToDef(Long userAnsw_id, Long def_id, String text) {
        this.userAnsw_id = userAnsw_id;
        this.def_id = def_id;
        this.text = text;
    }
}
