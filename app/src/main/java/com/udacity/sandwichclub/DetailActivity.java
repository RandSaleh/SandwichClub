package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .error(R.mipmap.ic_launcher)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        // Initialize and find the TextView .............

        TextView txtorigin = findViewById(R.id.origin_tv);

        TextView txtalsoKnownAs = findViewById(R.id.also_known_tv);
        TextView txtdescription = findViewById(R.id.description_tv);
        TextView txtingredients = findViewById(R.id.ingredients_tv);
        ////////---------------------- Lables ---------------------
        TextView lblDesc = findViewById(R.id.description_label);
        TextView lblalsoKnown = findViewById(R.id.also_known_as_label);
        TextView lblOri = findViewById(R.id.origin_label);
        TextView lblIng = findViewById(R.id.ingredients_label);

        //////////---------------------------------------------




        // Get the PlaceOfOrigin
        String originText = sandwich.getPlaceOfOrigin();
        if (originText.isEmpty()){
            txtorigin.setVisibility(View.GONE);
            lblOri.setVisibility(View.GONE);

        } else {
            txtorigin.setText(originText);
        }


///////////-----------------
        List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();

        if (alsoKnownAsList.size() == 0){
            lblalsoKnown.setVisibility(View.GONE);
            txtalsoKnownAs.setVisibility(View.GONE);
        } else {
            lblalsoKnown.setVisibility(View.VISIBLE);
            txtalsoKnownAs.setVisibility(View.VISIBLE);


            StringBuilder otherNames = new StringBuilder();

            for (String otherName : alsoKnownAsList) {
                otherNames.append(otherName).append(", ");
            }

            otherNames.setLength(otherNames.length() - 2);


            txtalsoKnownAs.setText(otherNames);
        }



        List<String> ingredientsList = sandwich.getIngredients();

        if (ingredientsList.size() == 0){
            lblIng.setVisibility(View.GONE);
            txtingredients.setVisibility(View.GONE);

        } else {
            StringBuilder ingredients = new StringBuilder();


            for (String ingredient : ingredientsList) {
                ingredients.append(ingredient).append(", ");
            }

            ingredients.setLength(ingredients.length() - 2);


            txtingredients.setText(ingredients);
        }






        String description = sandwich.getDescription();

        if (description.isEmpty()) {
            txtdescription.setVisibility(View.GONE);
        } else {
            txtdescription.setText(description);
        }






    }
}
