package com.onthego.onthego.api.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.onthego.onthego.models.Place;

import java.lang.reflect.Type;

public class PlaceDeserializer implements JsonDeserializer<Place> {

    @Override
    public Place deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Place place = new Place();
        JsonObject jsonObject = json.getAsJsonObject();
        place.setId(jsonObject.get("_id").getAsString());

        String name = jsonObject.get("user").getAsString();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        place.setName(name);

        switch (name) {
            case "Latia":
                place.setImage("https://images.pexels.com/photos/110813/pexels-photo-110813.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
                break;
            case "Pizzeria":
                place.setImage("https://images.pexels.com/photos/724216/pexels-photo-724216.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
                break;
            case "Crazyfood":
                place.setImage("https://images.pexels.com/photos/59943/pexels-photo-59943.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940È");
                break;
        }
        return place;
    }
}
