package com.team.app.backend.persistance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerToSeqOption {
    Long userAnsw_id;
    Long seqOpt_id;
    Integer serial_num;

    public AnswerToSeqOption(Long userAnsw_id, Long seqOpt_id, Integer serial_num) {
        this.userAnsw_id = userAnsw_id;
        this.seqOpt_id = seqOpt_id;
        this.serial_num = serial_num;
    }
}
