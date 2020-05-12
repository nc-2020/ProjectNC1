package com.team.app.backend.persistance.model;

public class SeqOption extends OptionObj {
    private Integer serial_num;
    private String text;
    private byte[] image;


    public SeqOption() {

    }

    public SeqOption(Long id, Integer serial_num, String text, byte[] image, Long quest_id) {
        super(id, quest_id);
        this.serial_num = serial_num;
        this.text = text;
        this.image = image;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
