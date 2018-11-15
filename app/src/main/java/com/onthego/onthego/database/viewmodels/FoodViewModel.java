package com.onthego.onthego.database.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.onthego.onthego.database.repositories.FoodRepository;
import com.onthego.onthego.models.Food;
import com.onthego.onthego.models.Place;

import java.util.List;

public class FoodViewModel extends AndroidViewModel{

    private FoodRepository foodRepository;
    private LiveData<List<Food>> foods;

    public FoodViewModel(@NonNull Application application) {
        super(application);
        foodRepository = new FoodRepository(application);
        foods = foodRepository.getFoods();
    }

    public LiveData<List<Food>> getFoods() {
        foods = foodRepository.getFoods();
        return foods;
    }

    public LiveData<List<Food>> getFoods(String place) {
        foods = foodRepository.getFoods(place);
        return foods;
    }

    public void insertFoods(List<Food> foods){
        foodRepository.insertFood(foods);
    }
}
