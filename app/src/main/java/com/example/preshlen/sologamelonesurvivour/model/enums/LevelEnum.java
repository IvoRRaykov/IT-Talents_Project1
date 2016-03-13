package com.example.preshlen.sologamelonesurvivour.model.enums;

public enum LevelEnum {
    LEVEL1(1), LEVEL2(2), LEVEL3(3);

    private int level;

    LevelEnum(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static LevelEnum getEnumByLevel(int level) {
        switch (level) {
            case 1:
                return LEVEL1;
            case 2:
                return LEVEL2;
            case 3:
                return LEVEL3;
        }
        return null;
    }

};