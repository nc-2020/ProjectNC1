package com.team.app.backend.persistance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerToOption {

    private Long userAnsw_id;
    private Long opt_id;


    public AnswerToOption(Long userAnsw_id, Long opt_id) {
        this.userAnsw_id = userAnsw_id;
        this.opt_id = opt_id;
    }
}
