package com.example.preshlen.sologamelonesurvivour.model.enums;

public enum ColorEnum {
    RED(1), BLUE(2);

    private int color;

    ColorEnum(int level) {
        this.color = level;
    }

    public int getColor() {
        return color;
    }

    public static ColorEnum getEnumByNumber(int number) {
        switch (number) {
            case 1:
                return RED;
            case 2:
                return BLUE;
        }
        return null;
    }


};