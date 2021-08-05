package com.ptran052.rockpaperscissors;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class JsonUtil {

    public static JSONObject toJson(Player player){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", player.getName());
            jsonObject.put("gold", player.getGold());
            JSONArray jsonArray = new JSONArray();
            ArrayList<Card> deck = player.getDeck();
            for (int i = 0; i < deck.size(); i++) {
                Card card = deck.get(i);
                JSONObject cardJson = new JSONObject();
                cardJson.put("name", card.getName());
                cardJson.put("type", card.getType());
                cardJson.put("power", card.getPower());
                cardJson.put("inDeck", card.getInDeck());
                jsonArray.put(cardJson);
            }
            jsonObject.put("deck", jsonArray);

            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void writeJson(Context context, JSONObject jsonObject) {
        String fileName = "data.json";
        String fileContent = jsonObject.toString();

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(fileContent);
            outputStreamWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readJson(Context context) {
        String fileName = "data.json";
        String jsonStr = "";

        try {
            InputStream inputStream = context.openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String readStr = "";
                StringBuilder builder = new StringBuilder();

                while ((readStr = bufferedReader.readLine()) != null) {
                    builder.append(readStr);
                }
                inputStream.close();
                jsonStr = builder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "noData";
        }

        return jsonStr;
    }
}
