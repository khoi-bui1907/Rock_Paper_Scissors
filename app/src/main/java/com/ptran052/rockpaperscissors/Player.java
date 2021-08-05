package com.ptran052.rockpaperscissors;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private String name;
    private int gold;
    private ArrayList<Card> deck;

    public Player(String name, int gold, ArrayList<Card> deck) {
        this.name = name;
        this.gold = gold;
        this.deck = deck;
    }

    public Player() {
        name = "";
        gold = 0;
        deck = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }
}
