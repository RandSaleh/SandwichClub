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
    public static String NAME = "name";
    public static String MAIN_NAME = "mainName";
    public static String ALSO_KNOWN_AS = "alsoKnownAs";
    public static String PLACE_OF_ORIGIN = "placeOfOrigin";
    public static String DESCRIPTION = "description";
    public static String IMAGE = "image";
    public static String INGREDIANT = "ingredients";
    private static Sandwich sand;


    public static Sandwich parseSandwichJson(String json) {
        try {

            JSONObject Starter = new JSONObject(json); /// initializing

            JSONObject name = Starter.getJSONObject(NAME);
            //--- parsing name

            String mainName = name.getString(MAIN_NAME);
            JSONArray alsoKnownAs_JSONArray = name.getJSONArray(ALSO_KNOWN_AS);

            //--- parsing placeOfOrigin

            String placeOfOrigin = Starter.getString(PLACE_OF_ORIGIN);

            //--- parsing description

            String description = Starter.getString(DESCRIPTION);


            String image = Starter.getString(IMAGE);


            JSONArray ingredients_JSONArray = Starter.getJSONArray(INGREDIANT);


            ///-------------- converting arrayJSON to Arrylist

            List<String> ingredients_List = convertJSONArrayToList(ingredients_JSONArray);


            List<String> alsoKnownAs_List = convertJSONArrayToList(alsoKnownAs_JSONArray);

            ///-------------- converting  array to Arrylist


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
