package com.onthego.onthego.database.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.onthego.onthego.database.repositories.PlaceRepository;
import com.onthego.onthego.models.Place;

import java.util.List;

public class PlaceViewModel extends AndroidViewModel{

    private PlaceRepository placeRepository;
    private LiveData<List<Place>> places;

    public PlaceViewModel(@NonNull Application application) {
        super(application);
        placeRepository = new PlaceRepository(application);
        places = placeRepository.getPlaces();
    }

    public LiveData<List<Place>> getPlaces() {
        return places;
    }

    public void insertPlaces(List<Place> places){
        placeRepository.insertPlaces(places);
    }
}
