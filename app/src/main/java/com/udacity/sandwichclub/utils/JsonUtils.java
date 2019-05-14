package com.udacity.sandwichclub.utils;

import android.widget.Toast;

import com.udacity.sandwichclub.MainActivity;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class JsonUtils {
    private final List<String> alsoKnownAs = null;
    private final List<String> ingredients = null;
    public static final String NAME = "name";
    public static final  String MAIN_NAME = "mainName";
    public static final String ALSO_KNOWN_AS = "alsoKnownAs";
    public static final  String PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String DESCRIPTION = "description";
    public static final  String IMAGE = "image";
    public static final String INGREDIANT = "ingredients";
    private static Sandwich sand;

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject Starter = new JSONObject(json); /// initializing
            JSONObject name = Starter.optJSONObject(NAME);
            String mainName = name.optString(MAIN_NAME);
            JSONArray alsoKnownAs_JSONArray = name.optJSONArray(ALSO_KNOWN_AS);
            String placeOfOrigin = Starter.optString(PLACE_OF_ORIGIN);
            String description = Starter.optString(DESCRIPTION);
            String image = Starter.optString(IMAGE);
            JSONArray ingredients_JSONArray = Starter.optJSONArray(INGREDIANT);
            List<String> ingredients_List = convertJSONArrayToList(ingredients_JSONArray);
            List<String> alsoKnownAs_List = convertJSONArrayToList(alsoKnownAs_JSONArray);
            sand = new Sandwich(mainName, alsoKnownAs_List, placeOfOrigin, description, image, ingredients_List);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sand;
    }

    public static ArrayList<String> convertJSONArrayToList(JSONArray arrj) throws Exception {
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < arrj.length(); i++) {
            temp.add(arrj.getString(i));
        }
        return temp;

    }
}
