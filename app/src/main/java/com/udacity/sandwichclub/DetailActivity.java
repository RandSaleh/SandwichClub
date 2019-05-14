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

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.image_iv)
    ImageView ingredientsIv;
    @BindView(R.id.origin_tv)
    TextView txtorigin;
    @BindView(R.id.also_known_tv)
    TextView txtalsoKnownAs;
    @BindView(R.id.description_tv)
    TextView txtdescription;
    @BindView(R.id.ingredients_tv)
    TextView txtingredients;

    @BindView(R.id.also_known_as_label)
    TextView lblalsoKnown;
    @BindView(R.id.origin_label)
    TextView lblOri;
    @BindView(R.id.ingredients_label)
    TextView lblIng;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
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
        String originText = sandwich.getPlaceOfOrigin();
        if (originText.isEmpty()) {
            txtorigin.setVisibility(View.GONE);
            lblOri.setVisibility(View.GONE);

        } else {
            txtorigin.setText(originText);
        }

        List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();

        if (alsoKnownAsList.size() == 0) {
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

        if (ingredientsList.size() == 0) {
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
