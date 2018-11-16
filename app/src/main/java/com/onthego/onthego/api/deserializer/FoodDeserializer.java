package com.onthego.onthego.api.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.onthego.onthego.R;
import com.onthego.onthego.models.Food;

import java.lang.reflect.Type;

public class FoodDeserializer implements JsonDeserializer {

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Food food = new Food();
        JsonObject jsonObject = json.getAsJsonObject();
        food.setId(jsonObject.get("_id").getAsString());

        String name = jsonObject.get("title").getAsString();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        food.setName(name);

        String desc = jsonObject.get("description").getAsString();
        desc = desc.substring(0, 1).toUpperCase() + desc.substring(1);
        food.setDescription(desc);

        String prop = jsonObject.get("propietario").getAsString();
        prop = prop.substring(0, 1).toUpperCase() + prop.substring(1);
        food.setPlaceId(prop);

        food.setPrice(jsonObject.get("precio").getAsFloat());

        //food.setImage(jsonObject.get("image").getAsString());
        switch (name) {
            case "Pupusas de queso":
                food.setImage("https://cdn-pro.elsalvador.com/wp-content/uploads/2017/01/31073612/1478814232233-e1500245574512.jpg");
                break;
            case "Pupusas de frijol con que":
                food.setName("Pupusas de frijol con queso");
                food.setImage("https://elsalvadoreshermoso.com/wp-content/uploads/2012/12/pupusas-frijol-queso.jpg");
                break;
            case "Papas fritas":
                food.setImage("https://www.litehousefoods.com/sites/default/files/livinglitehouseblog/wp-content/uploads/2014/02/flickr-com.jpg");
                break;
            case "Burrito":
                food.setImage("https://www.oldelpaso.co.uk/-/media/oep/uk/recipes/easy-chicken-bean-burritos-hero.jpg");
                break;
            case "Nachos":
                food.setImage("https://www.yellowblissroad.com/wp-content/uploads/2017/01/Buffalo-Chicken-Nachos1.jpg");
                break;
            case "Vegetariana":
                food.setName("Pizza vegetariana");
                food.setImage("https://assets.kraftfoods.com/recipe_images/opendeploy/172329_640x428.jpg");
                break;
            case "Carnibora":
                food.setName("Pizza carnivora");
                food.setImage("https://www.cbc.ca/inthekitchen/assets_c/2011/09/MeatLoversPizza1709-thumb-596x350-121624.jpg");
                break;
            case "Peperoni":
                food.setName("Pizza de pepperoni");
                food.setImage("https://imagesvc.timeincapp.com/v3/mm/image?url=https%3A%2F%2Fcdn-image.myrecipes.com%2Fsites%2Fdefault%2Ffiles%2Fstyles%2Fmedium_2x%2Fpublic%2Fimage%2Frecipes%2Fsl%2F03142008%2Fpepperoni-pizza-sl-1599569-x.jpg%3Fitok%3Dga1g5wpq&w=1000&c=sc&poi=face&q=70");
                break;
        }
        return food;
    }
}
