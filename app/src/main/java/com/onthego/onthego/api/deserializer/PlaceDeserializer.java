package com.onthego.onthego.api.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.onthego.onthego.models.Place;

import java.lang.reflect.Type;

public class PlaceDeserializer implements JsonDeserializer<Place>{

    @Override
    public Place deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Place place = new Place();
        JsonObject jsonObject = json.getAsJsonObject();
        place.setId(jsonObject.get("_id").getAsString());
        place.setName(jsonObject.get("user").getAsString());
        return place;
    }
}
