package com.example.preshlen.sologamelonesurvivour.model;

import com.example.preshlen.sologamelonesurvivour.model.enums.ColorEnum;
import com.example.preshlen.sologamelonesurvivour.model.enums.LevelEnum;

public class Zone {

    private String name;
    private LevelEnum level;
    private ColorEnum color;
    private int points;
    private int lifes;
    private int imageID;

    public Zone(String name, LevelEnum level, int imageID) {
        this.name = name;
        this.level = level;
        this.imageID = imageID;
        switch (level) {
            case LEVEL1:
                this.points = 100;
                break;
            case LEVEL2:
                this.points = 200;
                break;
            case LEVEL3:
                this.points = 400;
                break;
            default:
                break;
        }
        this.lifes = level.getLevel();
        color = null; // ?? :D
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getImageID() {
        return imageID;
    }

    public void setColor(ColorEnum color) {
        this.color = color;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public ColorEnum getColor() {
        return color;
    }

    public int getLifes() {
        return lifes;
    }

    @Override
    public String toString() {
        return this.name + " | " + this.level;
    }

    //
    // @Override
    // public int compareTo(Zone toCmp) {
    // if(this.name.compareTo(toCmp.getName()) > 0) return 1;
    // else if(this.name.compareTo(toCmp.getName()) < 0) return -1;
    //
    // return 0;
    // }

}