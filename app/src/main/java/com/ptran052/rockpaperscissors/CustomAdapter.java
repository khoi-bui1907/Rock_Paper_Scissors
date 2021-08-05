package com.ptran052.rockpaperscissors;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Card> {

    Activity context;
    int resource;
    List<Card> objects;
    Integer count;
    ConstraintLayout custom;
    /** init*/
    public CustomAdapter(Activity context, int resource, List<Card> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater= this.context.getLayoutInflater();
        final View row = inflater.inflate(this.resource,null);

        /** Set outlet*/
        custom = (ConstraintLayout) row.findViewById(R.id.custom);
        final TextView txtName = (TextView) row.findViewById(R.id.name);
        final TextView txtPower = (TextView) row.findViewById(R.id.power);
        ImageView imgCard = (ImageView) row.findViewById(R.id.image);
        final CheckBox chosenBox = (CheckBox) row.findViewById(R.id.chosenBox);
        final Card card = this.objects.get(position);

        /** Count number of chosen card*/
        count = 0;
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getInDeck().equals(true)) {
                count++;
            }
        }
        CardList.txtChosenNum.setText(count.toString() + "/7");

        /** Set data to row and set row layout*/
        txtName.setText(card.getName());
        txtPower.setText(card.getPower().toString());
        final int imageId = context.getResources().getIdentifier(card.getName(), "drawable", context.getPackageName());
        imgCard.setImageResource(imageId);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CardDetail.class);
                intent.putExtra("name", card.getName());
                intent.putExtra("power", card.getPower());
                intent.putExtra("imageID", imageId);
                context.startActivity(intent);
            }
        });
        setCell(card, row, chosenBox, txtName, txtPower);

        /** Set data to row*/
        chosenBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CardList.editButton.setVisibility(View.VISIBLE);
                if (isChecked == true) {
                    card.setInDeck(true);
                    setCell(card, row, chosenBox, txtName, txtPower);
                } else {
                    card.setInDeck(false);
                    setCell(card, row, chosenBox, txtName, txtPower);
                }

                /** Re-Count how many cards are chosen*/
                count = 0;
                for (int i = 0; i < objects.size(); i++) {
                    if (objects.get(i).getInDeck()) {
                        count++;
                        if (count > 7) {
                            Toast.makeText(context, "FULL CARD", Toast.LENGTH_SHORT).show();
                            card.setInDeck(false);
                            setCell(card, row, chosenBox, txtName, txtPower);
                            count--;
                            chosenBox.setChecked(false);
                        }
                    }
                }
                CardList.txtChosenNum.setText(count.toString() + "/7");
            }
        });

        CardList.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < objects.size(); i++) {
                    
                }
            }
        });
        return row;
    }

    void setCell(Card card, View cell, CheckBox checkBox, TextView txtName, TextView txtPower) {
        for (int i = 0; i < objects.size(); i++) {
            if (card.getInDeck()) {
                checkBox.setChecked(true);
                cell.setBackgroundResource(R.drawable.customborder);
                txtName.setTextColor(Color.WHITE);
                txtPower.setTextColor(Color.WHITE);
            } else {
                cell.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                txtName.setTextColor(Color.DKGRAY);
                txtPower.setTextColor(Color.DKGRAY);
            }
        }
    }
}
