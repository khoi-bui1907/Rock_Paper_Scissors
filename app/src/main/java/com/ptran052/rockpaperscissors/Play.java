package com.ptran052.rockpaperscissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class Play extends AppCompatActivity implements Serializable {
    private ArrayList<Card> currentdeck;
    private ArrayList<Card> botdeck;
    private Player player;
    private TextView playername;
    private Button backbtn;
    private Button forwardbtn;
    private TextView playerpoint;
    private TextView botpoint;
    private ImageView playercard;
    private ArrayList<Card> onhanddeck;
    private Button confirm;
    private ArrayList<Card> shuffleddeck;
    private TextView cardnum;
    private TextView turn;
    private int i =0;
    private int j ;
    private int shufflesize;
    private int playerpointdisplay ;
    private int botpointdisplay;
    private int totalPlayerpoint;
    private int totalBotpoint;
    private Boolean endgame =  false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        player = (Player) getIntent().getSerializableExtra("playerprepare") ;
        shuffleddeck = new ArrayList<>();
        shuffleddeck = (ArrayList<Card>) getIntent().getSerializableExtra("playershuffledeck") ;
        botdeck = new ArrayList<>();
        botdeck = (ArrayList<Card>) getIntent().getSerializableExtra("botdeck") ;


        shufflesize = shuffleddeck.size();
        currentdeck = player.getDeck();
        j = 11 - shufflesize;
        try {
            j = (int) getIntent().getSerializableExtra("turn");
            playerpointdisplay = (int) getIntent().getSerializableExtra("playerpoint");
            botpointdisplay = (int) getIntent().getSerializableExtra("botpoint");
            if(j!=1)
                totalPlayerpoint = (int) getIntent().getSerializableExtra("pushedtotalPlayerpoint");
            totalBotpoint = (int)getIntent().getSerializableExtra("pushedtotalBotpoint");
        }catch (Throwable e){
            e.printStackTrace();
        }
        playername = (TextView)findViewById(R.id.playername);
        playername.setText(player.getName());
        backbtn = (Button)findViewById(R.id.back);
        forwardbtn = (Button)findViewById(R.id.forward);
        playerpoint = (TextView) findViewById(R.id.playerpoint);
        playerpoint.setText("0");
        botpoint = (TextView) findViewById(R.id.botpoint);
        botpoint.setText("0");
        playercard = (ImageView)findViewById(R.id.playercard);
        confirm = (Button)findViewById(R.id.confirm);
        cardnum = (TextView)findViewById(R.id.cardnum);
        turn = (TextView)findViewById(R.id.turntv) ;
        ready();
    }

    private void ready() {
        String tempcard = shuffleddeck.get(0).getName();
        int imageId = getResources().getIdentifier(tempcard, "drawable",getPackageName());
        playercard.setImageResource(imageId);
        backbtn.setEnabled(false);
        if (shufflesize>=3) {
            cardnum.setText("1/3");
        }

        if (shufflesize==2) {
            cardnum.setText("1/2");
        }
        if (shufflesize==1) {
            cardnum.setText("1/1");
            forwardbtn.setEnabled(false);
            endgame = true;
        }
        if(j==1){
            totalPlayerpoint = 0;
            totalBotpoint = 0;
        }
        turn.setText(j + "/10");
        totalPlayerpoint += playerpointdisplay;
        totalBotpoint += botpointdisplay;
        playerpoint.setText(String.valueOf(totalPlayerpoint));
        botpoint.setText(String.valueOf(totalBotpoint));

    }

    public void onBackBtnClicked(View view) {
        if (shufflesize>=3) {
            confirm.setEnabled(false);
            forwardbtn.setEnabled(true);
            i--;
            if (i == 0) {
                backbtn.setEnabled(false);
            }
            String tempcard = shuffleddeck.get(i).getName();
            int imageId = getResources().getIdentifier(tempcard, "drawable", getPackageName());
            playercard.setImageResource(imageId);
            cardnum.setText(i + 1 + "/3");
        }
        else if (shufflesize==2){
            confirm.setEnabled(false);
            forwardbtn.setEnabled(true);
            i--;
            if (i == 0) {
                backbtn.setEnabled(false);
            }
            String tempcard = shuffleddeck.get(i).getName();
            int imageId = getResources().getIdentifier(tempcard, "drawable", getPackageName());
            playercard.setImageResource(imageId);
            cardnum.setText(i + 1 + "/2");
        }
        else if (shufflesize==1){

        }
    }

    public void onForwardBtnClick(View view) {
        if (shufflesize>=3) {
            confirm.setEnabled(false);
            backbtn.setEnabled(true);
            i++;
            if (i == 2) {
                forwardbtn.setEnabled(false);
            }
            String tempcard = shuffleddeck.get(i).getName();
            int imageId = getResources().getIdentifier(tempcard, "drawable", getPackageName());
            playercard.setImageResource(imageId);
            cardnum.setText(i + 1 + "/3");
        }else if (shufflesize == 2) {
            confirm.setEnabled(false);
            backbtn.setEnabled(true);
            i++;
            if (i == 1) {
                forwardbtn.setEnabled(false);
            }
            String tempcard = shuffleddeck.get(i).getName();
            int imageId = getResources().getIdentifier(tempcard, "drawable", getPackageName());
            playercard.setImageResource(imageId);
            cardnum.setText(i + 1 + "/2");
        }
        else if (shufflesize == 1){

        }
    }

    public void onConfirmClick(View view) {
        j++;
        Intent icompare = new Intent(Play.this,Result.class);
        icompare.putExtra("playerresult", player);
        icompare.putExtra("cardselect", shuffleddeck);
        icompare.putExtra("botcard",botdeck);
        icompare.putExtra("index",i);
        icompare.putExtra("turn",j);
        icompare.putExtra("playerplaypoint",Integer.parseInt(playerpoint.getText().toString()));
        icompare.putExtra("botplaypoint",Integer.parseInt(botpoint.getText().toString()));
        icompare.putExtra("totalPlayerpoint",totalPlayerpoint);
        icompare.putExtra("totalBotpoint",totalBotpoint);
        icompare.putExtra("endGame",endgame);
        startActivity(icompare);
    }

    public void onCardClick(View view) {
        confirm.setEnabled(true);
    }


}
