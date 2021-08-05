package com.ptran052.rockpaperscissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Preparation extends AppCompatActivity implements Serializable {
    private Player player;
    private ArrayList<Card> currentdeck;
    private ArrayList<Card> shuffleddeck;
    private ArrayList<Card> botdeck;
    private String name;
    private String deck;
    //private ArrayList<Card> CarlDeck,MikeDeck,LyzDeck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparation);
        player = (Player) getIntent().getSerializableExtra("playerplay") ;
        currentdeck = player.getDeck();
        System.out.println(player.getDeck().get(1).getInDeck() + "----------");

        shuffleddeck = new ArrayList<>();
        botdeck = new ArrayList<>();
        for ( int i = 0 ; i < currentdeck.size();i++){
            if(currentdeck.get(i).getInDeck()){
                Card temp = currentdeck.get(i);
                shuffleddeck.add(temp);
            }
        }

        shuffle(shuffleddeck);
        botdeckinit(botdeck);
        botdeckshuffle(botdeck);
    }

    private void botdeckshuffle(ArrayList<Card> deck) {
        Collections.shuffle(deck);
    }

    private void botdeckinit(ArrayList<Card> deck) {
        Random r = new Random();
        int irandom = (r.nextInt(4-1) + 1);
        switch (irandom){
            case 1:
                for (int i = 0; i < 2; i++) {
                    Card card = new Card("normal_rock", "rock", 100, true);
                    deck.add(card);
                    Card card1 = new Card("normal_scissors", "scissors", 100, true);
                    deck.add(card1);
                    Card card2 = new Card("normal_paper", "paper", 100, true);
                    deck.add(card2);
                    Card card3 = new Card("normal_rock", "rock", 100, true);
                    deck.add(card3);
                    Card card4 = new Card("normal_scissors", "scissors", 100, true);
                    deck.add(card4);
                }
                break;

            case 2:
                for (int i = 0; i < 2; i++) {
                    Card card = new Card("rare_rock", "rock", 200, true);
                    deck.add(card);
                    card = new Card("rare_scissors", "scissors", 200, true);
                    deck.add(card);
                    card = new Card("rare_paper", "paper", 200, true);
                    deck.add(card);
                    card = new Card("normal_rock", "rock", 100, true);
                    deck.add(card);
                    card = new Card("normal_scissors", "scissors", 100, true);
                    deck.add(card);
                }
                break;
            case 3:
                for (int i = 0; i < 2; i++) {
                    Card card = new Card("epic_rock", "rock", 400, true);
                    deck.add(card);
                    Card card1 = new Card("epic_scissors", "scissors", 400, true);
                    deck.add(card1);
                    Card card2 = new Card("epic_paper", "paper", 400, true);
                    deck.add(card2);
                   Card  card3 = new Card("rare_rock", "rock", 200, true);
                    deck.add(card3);
                   Card card4 = new Card("rare_scissors", "scissors", 200, true);
                    deck.add(card4);
                }
                break;
            default:
                for (int i = 0; i < 2; i++) {
                    Card card = new Card("epic_rock", "rock", 400, true);
                    deck.add(card);
                    card = new Card("epic_scissors", "scissors", 400, true);
                    deck.add(card);
                    card = new Card("epic_paper", "paper", 400, true);
                    deck.add(card);
                    card = new Card("rare_rock", "rock", 200, true);
                    deck.add(card);
                    card = new Card("rare_scissors", "scissors", 200, true);
                    deck.add(card);
                }
                break;
        }

    }

//    private String generatebotnames(String name1 , String name2, String name3) {
//        Random r = new Random();
//        int randomi = r.nextInt(3-1) + 1;
//        switch (randomi) {
//            case 1:
//                name = name1;
//                break;
//            case 2:
//                name = name2;
//                break;
//            case 3:
//                name = name3;
//                break;
//            default:
//        }
//        return name;
//
//    }
//
//    private void generatebotdecks(String namebot) {
//        if (namebot.equals(Carl.getName())){
//            Collections.shuffle(Carl.getDeck());
//        }
//        else if (namebot.equals(Mike.getName())){
//            Collections.shuffle(Mike.getDeck());
//        }
//        else if (namebot.equals(Lyz.getName())){
//            Collections.shuffle(Lyz.getDeck());
//        }
//
//    }

    private void shuffle(ArrayList<Card> deck) {
        Collections.shuffle(deck);
    }


    public void onletplaybuttonClicked(View view) {
        Intent i = new Intent(Preparation.this,Play.class);
        i.putExtra("playerprepare", player);
        i.putExtra("playershuffledeck", shuffleddeck);
        System.out.println(botdeck +"");
        i.putExtra("botdeck", botdeck);
        startActivity(i);
    }
}
