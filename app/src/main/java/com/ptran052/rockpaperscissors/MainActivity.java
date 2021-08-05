package com.ptran052.rockpaperscissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {
    Player player ;
    Card card ;
    private Button playbtn;
    private Button deckbtn;
    private Button shopbtn;
    private TextView tv;
//    private ArrayList<Card> currentdeck;
//    private ArrayList<Card> botdeck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = (Player) getIntent().getSerializableExtra("player") ;

        playbtn = (Button) findViewById(R.id.playbtn);
        deckbtn = (Button) findViewById(R.id.deckbtn);
        shopbtn = (Button) findViewById(R.id.shopbtn);

    }

    public void toCardList(View view) {
        Intent intent = new Intent(MainActivity.this, CardList.class);
        startActivity(intent);

    }

    public void onPlayPressed(View view) {
        Intent iplay = new Intent(MainActivity.this,Preparation.class);
        iplay.putExtra("playerplay", player);
        startActivity(iplay);
    }
}
