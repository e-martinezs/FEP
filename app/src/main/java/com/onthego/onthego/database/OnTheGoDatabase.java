package com.onthego.onthego.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.onthego.onthego.database.daos.FoodDao;
import com.onthego.onthego.database.daos.OrderDao;
import com.onthego.onthego.database.daos.PlaceDao;
import com.onthego.onthego.models.Food;
import com.onthego.onthego.models.Order;
import com.onthego.onthego.models.Place;

@Database(entities = {Food.class, Place.class, Order.class}, version = 1)
public abstract class OnTheGoDatabase extends RoomDatabase{

    public abstract FoodDao foodDao();

    public abstract PlaceDao placeDao();

    public abstract OrderDao orderDao();

    private static OnTheGoDatabase INSTANCE;

    public static OnTheGoDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (OnTheGoDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), OnTheGoDatabase.class, "onthego_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
