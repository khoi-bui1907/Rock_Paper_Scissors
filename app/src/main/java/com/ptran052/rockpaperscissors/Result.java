package com.ptran052.rockpaperscissors;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class Result extends AppCompatActivity implements Serializable {
    private Player player;
    private ArrayList<Card> onHandDeck;
    private ArrayList<Card> BotDeck;
    private ImageView playercard;
    private ImageView botcard;
    private int index ;
    private TextView playername;
    private String status;
    private TextView playerpoint;
    private TextView botpoint;
    private TextView statustv;
    private int playerpointfinal;
    private int botpointfinal;
    private int tempplayerpoint;
    private int tempbotpoint;
    private int totalBotpoint;
    private int totalPlayerpoint;
    final Handler handler = new Handler();
    private int turn =1;
    private Boolean endGame ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        player = (Player) getIntent().getSerializableExtra("playerresult") ;
        onHandDeck = (ArrayList<Card>)getIntent().getSerializableExtra("cardselect");
        BotDeck = (ArrayList<Card>)getIntent().getSerializableExtra("botcard");
        index = (Integer)getIntent().getSerializableExtra("index");
        turn = (int)getIntent().getSerializableExtra("turn");
        tempplayerpoint = (int)getIntent().getSerializableExtra("playerplaypoint");
        tempbotpoint = (int)getIntent().getSerializableExtra("botplaypoint");
        totalBotpoint = (int)getIntent().getSerializableExtra("totalBotpoint");
        totalPlayerpoint = (int)getIntent().getSerializableExtra("totalPlayerpoint");
        endGame = (Boolean)getIntent().getSerializableExtra("endGame");

        playerpoint = (TextView)findViewById(R.id.resultplayerpoint) ;
        botpoint = (TextView)findViewById(R.id.resultbotpoint);
        statustv = (TextView)findViewById(R.id.status);

        playercard = (ImageView)findViewById(R.id.playercardresult);
        botcard = (ImageView)findViewById(R.id.botcardresult);

        String temp1 = onHandDeck.get(index).getName();
        int imageId1 = getResources().getIdentifier(temp1, "drawable",getPackageName());
        playercard.setImageResource(imageId1);

        String temp2 = BotDeck.get(index).getName();
        int imageId2 = getResources().getIdentifier(temp2, "drawable",getPackageName());
        botcard.setImageResource(imageId2);

        playername = (TextView)findViewById(R.id.resultplayername);
        playername.setText(player.getName());

        calculate();
        displayresult();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!endGame){
                    backActivity();
                }else{
                    totalBotpoint += botpointfinal;
                    totalPlayerpoint += playerpointfinal;
                    Intent intent = new Intent(Result.this, EndGameActivity.class);
                    intent.putExtra("player", player);
                    intent.putExtra("botPoint", totalBotpoint);
                    intent.putExtra("playerPoint", totalPlayerpoint);
                    startActivity(intent);
                }
            }
        }, 5000);

    }

    private void backActivity() {

        onHandDeck.remove(index);
        BotDeck.remove(index);
        Intent i2 = new Intent(Result.this,Play.class);
        i2.putExtra("playerprepare", player);
        i2.putExtra("playershuffledeck", onHandDeck);
        i2.putExtra("botdeck",BotDeck);
        i2.putExtra("turn",turn);
        i2.putExtra("playerpoint",Integer.parseInt(playerpoint.getText().toString()));
        i2.putExtra("pushedtotalPlayerpoint",totalPlayerpoint);
        i2.putExtra("pushedtotalBotpoint",totalBotpoint);
        i2.putExtra("botpoint",Integer.parseInt(botpoint.getText().toString()));

        startActivity(i2);
    }

    private void displayresult() {
        playerpointfinal=0;
        botpointfinal=0;
        int temp1;
        int temp2;
        switch (status){
            case "win" :
                temp1 = onHandDeck.get(index).getPower();
                playerpointfinal += temp1 ;
                playerpoint.setText(String.valueOf(playerpointfinal));
                statustv.setText("YOU WIN");
                break;
            case "draw" :
                temp1 = onHandDeck.get(index).getPower();
                temp2 = BotDeck.get(index).getPower();
                if (temp1 < temp2){
                    botpointfinal += temp2;
                    botpoint.setText(String.valueOf(botpointfinal));
                    statustv.setText("YOU LOSE");
                }
                else if(temp1>temp2){
                    playerpointfinal += temp1 ;
                    playerpoint.setText(String.valueOf(playerpointfinal));
                    statustv.setText("YOU WIN");
                } else  {
                    botpoint.setText("0");
                    playerpoint.setText("0");
                    statustv.setText("DRAW");
                }
                break;
            case "lose":
                temp2 = BotDeck.get(index).getPower();
                botpointfinal += temp2;
                botpoint.setText(String.valueOf(botpointfinal));
                statustv.setText("YOU LOSE");
                break;

        }

    }

    private void calculate() {
        String playertemptype = onHandDeck.get(index).getType();
        String bottemptype = BotDeck.get(index).getType();
        status = "";
        switch (playertemptype){
            case "rock":
                if (bottemptype.equals("scissors")) {
                    status = "win";
                }
                if (bottemptype.equals("rock")) {
                    status = "draw";
                }
                if (bottemptype.equals("paper")) {
                    status = "lose";
                }
                break;

            case "paper":
                if (bottemptype.equals("scissors")) {
                    status = "lose";
                }
                if (bottemptype.equals("rock")) {
                    status = "win";
                }
                if (bottemptype.equals("paper")) {
                    status = "draw";
                }
                break;

            case "scissors":
                if (bottemptype.equals("scissors")) {
                    status = "draw";
                }
                if (bottemptype.equals("rock")) {
                    status = "lose";
                }
                if (bottemptype.equals("paper")) {
                    status = "win";
                }
                break;
        }

    }
    public void waittodisplay(Integer i){
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
