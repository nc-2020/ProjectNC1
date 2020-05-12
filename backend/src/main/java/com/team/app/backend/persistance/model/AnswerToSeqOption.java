package com.team.app.backend.persistance.model;

public class AnswerToSeqOption {

    Long userAnsw_id;
    Long seqOpt_id;
    Integer serial_num;

    public AnswerToSeqOption() {}

    public AnswerToSeqOption(Long userAnsw_id, Long seqOpt_id, Integer serial_num) {
        this.userAnsw_id = userAnsw_id;
        this.seqOpt_id = seqOpt_id;
        this.serial_num = serial_num;
    }

    public Long getUserAnsw_id() {
        return userAnsw_id;
    }

    public AnswerToSeqOption setUserAnsw_id(Long userAnsw_id) {
        this.userAnsw_id = userAnsw_id;
        return this;
    }

    public Long getSeqOpt_id() {
        return seqOpt_id;
    }

    public AnswerToSeqOption setSeqOpt_id(Long seqOpt_id) {
        this.seqOpt_id = seqOpt_id;
        return this;
    }

    public Integer getSerial_num() {
        return serial_num;
    }

    public AnswerToSeqOption setSerial_num(Integer serial_num) {
        this.serial_num = serial_num;
        return this;
    }
}
