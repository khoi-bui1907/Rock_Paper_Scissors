package com.ptran052.rockpaperscissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class CardDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        TextView txtName = (TextView) findViewById(R.id.name);
        TextView txtPower = (TextView) findViewById(R.id.power);
        ImageView imgCard = (ImageView) findViewById(R.id.image);
        Intent intent = this.getIntent();
        try {
            String name = intent.getStringExtra("name");
            String power = intent.getStringExtra("power");
            Integer imageID = Integer.parseInt(intent.getStringExtra("imageID"));
            txtName.setText(name);
            txtPower.setText(power);
            imgCard.setImageResource(imageID);
        } catch (Exception e) {
            Log.d("ERROR", e.toString());
        }
    }
}
