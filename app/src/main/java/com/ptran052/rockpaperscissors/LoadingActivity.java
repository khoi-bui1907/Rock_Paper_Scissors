package com.ptran052.rockpaperscissors;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoadingActivity extends AppCompatActivity {


    String jsonStr;
    Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        jsonStr = JsonUtil.readJson(this);
        if (jsonStr.equals("noData")) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("WELCOME NEW USER!");
            alertDialog.setMessage("PLease tell us your name");

            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            alertDialog.setView(input);
            alertDialog.setPositiveButton("That's my name", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String name = input.getText().toString();
                    if (!name.isEmpty()) {
                        ArrayList<Card> defaultDeck = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            Card cardRock = new Card("normal_rock", "rock", 100, true);
                            defaultDeck.add(cardRock);
                            Card cardScissors = new Card("normal_scissors", "scissors", 100, true);
                            defaultDeck.add(cardScissors);
                            Card cardPaper = new Card("normal_paper", "paper", 100, true);
                            defaultDeck.add(cardPaper);
                        }
                        Card cardRare = new Card("rare_paper", "paper", 200, true);
                        defaultDeck.add(cardRare);
                        player = new Player(name, 1000, defaultDeck);
                        JSONObject jsonObject = JsonUtil.toJson(player);
                        JsonUtil.writeJson(LoadingActivity.this, jsonObject);
                        Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                        intent.putExtra("player", player);
                        startActivity(intent);
                    }
                }
            });
            alertDialog.setCancelable(false);
            alertDialog.show();
        }
        else {
            JSONObject jsonObject;
            System.out.println("This " +jsonStr);
            try {
                jsonObject = new JSONObject(jsonStr);
                System.out.println(jsonObject.getString("name"));
                player = new Player();
                player.setName(jsonObject.getString("name"));
                player.setGold(jsonObject.getInt("gold"));
                System.out.println(jsonObject.getJSONArray("deck"));
                JSONArray jsonArray = jsonObject.getJSONArray("deck");
                ArrayList<Card> deck = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    Card card = new Card();
                    card.setName(object.getString("name"));
                    card.setType(object.getString("type"));
                    card.setPower(object.getInt("power"));
                    card.setInDeck(object.getBoolean("inDeck"));
                    deck.add(card);
                }
                player.setDeck(deck);
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                intent.putExtra("player", player);
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
