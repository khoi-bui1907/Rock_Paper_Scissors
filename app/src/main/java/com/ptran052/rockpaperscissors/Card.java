package com.ptran052.rockpaperscissors;

import java.io.Serializable;

public class Card implements Serializable {
    private String name;
    private String type;
    private Integer power;
    private Boolean inDeck;

    public Card(String name, String type, Integer power, Boolean inDeck) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.inDeck = inDeck;
    }

    public Card() {
        name = "";
        type = "";
        power = 0;
        inDeck = false;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPower() {
        return power;
    }
    public void setPower(Integer power) {
        this.power = power;
    }

    public Boolean getInDeck() {return inDeck;}
    public void setInDeck(Boolean inDeck) {this.inDeck = inDeck;}
}
