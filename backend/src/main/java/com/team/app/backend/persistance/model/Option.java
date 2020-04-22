package com.team.app.backend.persistance.model;

public class Option {
    private Long id;
    private Boolean is_correct;
    private String text;
    private byte[] image;
    private Integer quest_id;

    public Option(Long id, Boolean is_correct, String text, byte[] image, Integer quest_id) {
        this.id = id;
        this.is_correct = is_correct;
        this.text = text;
        this.image = image;
        this.quest_id = quest_id;
    }

    public Long getId() {
        return id;
    }

    public Boolean getIs_correct() {
        return is_correct;
    }

    public void setIs_correct(Boolean is_correct) {
        this.is_correct = is_correct;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getQuest_id() {
        return quest_id;
    }

    public void setQuest_id(Integer quest_id) {
        this.quest_id = quest_id;
    }
}
