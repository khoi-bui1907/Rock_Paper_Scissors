package com.ptran052.rockpaperscissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CardList extends AppCompatActivity {
    ListView cardListView;
    static ArrayList<Card> cardArrayList;
    CustomAdapter customAdapter;
    static Button editButton;
    static TextView txtChosenNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        cardListView = (ListView) findViewById(R.id.cardListView);
        cardArrayList = new ArrayList<>();

        customAdapter = new CustomAdapter(this, R.layout.custom_list, cardArrayList);

        cardListView.setAdapter(customAdapter);

        txtChosenNum = (TextView) findViewById(R.id.txtChosenNum);

        editButton = (Button) findViewById(R.id.editButton);


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
