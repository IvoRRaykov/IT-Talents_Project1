package com.example.preshlen.sologamelonesurvivour.model.classes;

import java.io.Serializable;

public class Answer implements Serializable {

    private String text;
    private boolean isRight;

    public Answer(String text, boolean isRight) {
        this.text = text;
        this.isRight = isRight;
    }

    public String getText() {
        return text;
    }

    public boolean isRight() {
        return isRight;
    }

    @Override
    public String toString() {
        return text;
    }

}