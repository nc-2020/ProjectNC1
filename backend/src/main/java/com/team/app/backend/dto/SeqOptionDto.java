package com.team.app.backend.dto;

public class SeqOptionDto {
    private Integer serial_num;
    private String text;
    private String image;

    public SeqOptionDto() {
    }

    public Integer getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(Integer serial_num) {
        this.serial_num = serial_num;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
