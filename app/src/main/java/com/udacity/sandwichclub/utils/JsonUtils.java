package com.udacity.sandwichclub.utils;

import android.widget.Toast;

import com.udacity.sandwichclub.MainActivity;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class JsonUtils {
    private final   List<String> alsoKnownAs = null ;
    private final  List<String> ingredients = null ;

    private static  Sandwich sand ;




    public static Sandwich parseSandwichJson(String json)  {
        try {
///-------------------------------------------------------
       /* we recieve string contain json data in this form
        key :jsonObject -- key : string
                        -- key :ArrayJson
        key :string
        key : string
        key :string
        key : jsonArray

         */

///////// -----------------Start parsing -------------------------------

            JSONObject Starter = new JSONObject(json); /// initializing

            JSONObject name = Starter.getJSONObject("name");
            //--- parsing name

            String mainName = name.getString("mainName");
            JSONArray alsoKnownAs_JSONArray = name.getJSONArray("alsoKnownAs");

            //--- parsing placeOfOrigin

            String placeOfOrigin = Starter.getString("placeOfOrigin");

            //--- parsing description

            String description = Starter.getString("description");

            //-- parsing image

            String image = Starter.getString("image"); //this one

            //-- parsing ingredients

            JSONArray ingredients_JSONArray = Starter.getJSONArray("ingredients");


///////////////-----------------------------------------------------------------------


            ///-------------- converting arrayJSON to Arrylist

            List <String> ingredients_List = convertJSONArrayToList(ingredients_JSONArray);


            List <String> alsoKnownAs_List = convertJSONArrayToList(alsoKnownAs_JSONArray);

            ///-------------- converting  array to Arrylist


///----------------------- Assigning the value to the Sandwich Object -------------------
/* sandwich.setMainName(mainName);
 sandwich.setPlaceOfOrigin(placeOfOrigin);
 sandwich.setDescription(description);
 sandwich.setImage(image);
 sandwich.setIngredients(ingredients_List);
 sandwich.setAlsoKnownAs(alsoKnownAs_List);
*/
////----------------------------------------------------------------------------------------
            sand = new Sandwich(mainName, alsoKnownAs_List, placeOfOrigin, description, image, ingredients_List);

        }
        catch (Exception e){e.printStackTrace();}


        return sand;
    }




    public  static  ArrayList<String> convertJSONArrayToList(JSONArray arrj) throws  Exception {
        ArrayList <String>temp  = new ArrayList<String>();

        for (int i=0;i<arrj.length();i++){
            temp.add(arrj.getString(i));
        }



        return temp;

    }
}
