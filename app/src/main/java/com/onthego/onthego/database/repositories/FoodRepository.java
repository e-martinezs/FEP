package com.onthego.onthego.database.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.onthego.onthego.database.OnTheGoDatabase;
import com.onthego.onthego.database.daos.FoodDao;
import com.onthego.onthego.database.daos.PlaceDao;
import com.onthego.onthego.models.Food;
import com.onthego.onthego.models.Place;

import java.util.List;

public class FoodRepository {

    private FoodDao foodDao;
    private LiveData<List<Food>> foods;

    public FoodRepository(Application application){
        OnTheGoDatabase db = OnTheGoDatabase.getDatabase(application);
        foodDao = db.foodDao();
        foods = foodDao.getFoods();
    }

    public LiveData<List<Food>> getFoods() {
        foods = foodDao.getFoods();
        return foods;
    }

    public LiveData<List<Food>> getFoods(String place) {
        foods = foodDao.getFoods(place);
        return foods;
    }

    public void insertFood(List<Food> foods){
        new FoodRepository.insertFoodsAsyncTask(foodDao).execute(foods);
    }

    private static class insertFoodsAsyncTask extends AsyncTask<List<Food>, Void, Void> {
        private FoodDao foodDao;

        public insertFoodsAsyncTask(FoodDao foodDao){
            this.foodDao = foodDao;
        }

        @Override
        protected Void doInBackground(List<Food>... foods) {
            foodDao.deleteFood();
            for (Food f:foods[0]){
                foodDao.insertFood(f);
            }
            return null;
        }
    }

}
