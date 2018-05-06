package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    TextView nameTv,originTv,alsoKnownTv,descriptionTv,ingredientsTv;
    ImageView sandwichImageIv;
    Sandwich sandwich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        sandwichImageIv= findViewById(R.id.image_iv);
        nameTv=findViewById(R.id.main_name_tv);
        originTv=findViewById(R.id.origin_tv);
        alsoKnownTv=findViewById(R.id.also_known_tv);
        descriptionTv=findViewById(R.id.description_tv);
        ingredientsTv=findViewById(R.id.ingredients_tv);


        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(sandwichImageIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        nameTv.setText(sandwich.getMainName());
        originTv.setText(sandwich.getPlaceOfOrigin());
        descriptionTv.setText(sandwich.getDescription());
        String ingredientsText="";
        for (int i = 0; i <sandwich.getIngredients().size() ; i++) {
            ingredientsText+=sandwich.getIngredients().get(i)+", ";
        }
        ingredientsTv.setText(ingredientsText);


        String alsoKnownAsText="";
        for (int i = 0; i <sandwich.getAlsoKnownAs().size() ; i++) {
            alsoKnownAsText+=sandwich.getAlsoKnownAs().get(i)+" | ";
        }
        alsoKnownTv.setText(alsoKnownAsText);

    }
}
