package com.onthego.onthego.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.onthego.onthego.models.Food;
import com.onthego.onthego.models.Place;

import java.util.List;

@Dao
public interface FoodDao {

    @Query("SELECT * FROM food_table")
    LiveData<List<Food>> getFoods();

    @Query("SELECT * FROM food_table WHERE placeId LIKE :place")
    LiveData<List<Food>> getFoods(String place);

    @Query("DELETE FROM food_table")
    void deleteFood();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFood(Food food);
}
