package com.onthego.onthego.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.onthego.onthego.models.Place;

import java.util.List;

@Dao
public interface PlaceDao {

    @Query("SELECT * FROM place_table")
    LiveData<List<Place>> getPlaces();

    @Query("DELETE FROM place_table")
    void deletePlaces();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlace(Place place);
}
