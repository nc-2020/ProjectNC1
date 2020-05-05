package com.team.app.backend.persistance.model;

public class AnswerToOption {

    private Long userAnsw_id;
    private Long opt_id;

    public AnswerToOption() {}

    public AnswerToOption(Long userAnsw_id, Long opt_id) {
        this.userAnsw_id = userAnsw_id;
        this.opt_id = opt_id;
    }

    public Long getUserAnsw_id() {
        return userAnsw_id;
    }

    public AnswerToOption setUserAnsw_id(Long userAnsw_id) {
        this.userAnsw_id = userAnsw_id;
        return this;
    }

    public Long getOpt_id() {
        return opt_id;
    }

    public AnswerToOption setOpt_id(Long opt_id) {
        this.opt_id = opt_id;
        return this;
    }
}
