package com.onthego.onthego.api.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.onthego.onthego.models.Food;

import java.lang.reflect.Type;

public class FoodDeserializer implements JsonDeserializer {

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Food food = new Food();
        JsonObject jsonObject = json.getAsJsonObject();
        food.setId(jsonObject.get("_id").getAsString());
        food.setName(jsonObject.get("title").getAsString());
        food.setDescription(jsonObject.get("description").getAsString());
        //food.setImage(jsonObject.get("image").getAsString());
        food.setImage("");
        food.setPlaceId(jsonObject.get("propietario").getAsString());
        food.setPrice(jsonObject.get("precio").getAsFloat());
        return food;
    }
}
