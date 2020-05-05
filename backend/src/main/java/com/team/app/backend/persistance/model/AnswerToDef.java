package com.team.app.backend.persistance.model;

public class AnswerToDef {

    Long userAnsw_id;
    Long def_id;
    String text;

    public AnswerToDef() {}

    public AnswerToDef(Long userAnsw_id, Long def_id, String text) {
        this.userAnsw_id = userAnsw_id;
        this.def_id = def_id;
        this.text = text;
    }

    public Long getUserAnsw_id() {
        return userAnsw_id;
    }

    public AnswerToDef setUserAnsw_id(Long userAnsw_id) {
        this.userAnsw_id = userAnsw_id;
        return this;
    }

    public Long getDef_id() {
        return def_id;
    }

    public AnswerToDef setDef_id(Long def_id) {
        this.def_id = def_id;
        return this;
    }

    public String getText() {
        return text;
    }

    public AnswerToDef setText(String text) {
        this.text = text;
        return this;
    }
}
