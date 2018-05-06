package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich=new Sandwich();
        try {
            JSONObject jsonObject=new JSONObject(json);

            JSONObject jname=jsonObject.getJSONObject("name");
            String jmainName=jname.getString("mainName");

            JSONArray jalsoKnownAs=jname.getJSONArray("alsoKnownAs");
            List<String > alsoKnownAsList=new ArrayList<String>();
            for (int i = 0; i <jalsoKnownAs.length() ; i++) {
                alsoKnownAsList.add(jalsoKnownAs.getString(i));
            }
            String jplaceOfOrigin=jsonObject.getString("placeOfOrigin");
            String jdiscription=jsonObject.getString("description");
            String jimage=jsonObject.getString("image");

            JSONArray jingredients=jsonObject.getJSONArray("ingredients");
            List<String > ingredientsList=new ArrayList<String>();
            for (int i = 0; i <jingredients.length() ; i++) {
                ingredientsList.add(jingredients.getString(i));
            }

            sandwich.setMainName(jmainName);
            sandwich.setAlsoKnownAs(alsoKnownAsList);
            sandwich.setPlaceOfOrigin(jplaceOfOrigin);
            sandwich.setDescription(jdiscription);
            sandwich.setIngredients(ingredientsList);
            sandwich.setImage(jimage);
           // Log.d("sandwich",sandwich.toString());
            return sandwich;



        } catch (JSONException e) {
            e.printStackTrace();

        }

        return null;
    }
}
