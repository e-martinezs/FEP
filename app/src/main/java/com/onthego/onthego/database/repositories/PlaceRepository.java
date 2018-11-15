package com.onthego.onthego.database.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.onthego.onthego.database.OnTheGoDatabase;
import com.onthego.onthego.database.daos.PlaceDao;
import com.onthego.onthego.models.Place;

import java.util.List;

public class PlaceRepository {

    private PlaceDao placeDao;
    private LiveData<List<Place>> places;

    public PlaceRepository(Application application){
        OnTheGoDatabase db = OnTheGoDatabase.getDatabase(application);
        placeDao = db.placeDao();
        places = placeDao.getPlaces();
    }

    public LiveData<List<Place>> getPlaces() {
        return places;
    }

    public void insertPlaces(List<Place> places){
        new insertPlacesAsyncTask(placeDao).execute(places);
    }

    private static class insertPlacesAsyncTask extends AsyncTask<List<Place>, Void, Void>{
        private PlaceDao placeDao;

        public insertPlacesAsyncTask(PlaceDao placeDao){
            this.placeDao = placeDao;
        }

        @Override
        protected Void doInBackground(List<Place>... places) {
            placeDao.deletePlaces();
            for (Place p:places[0]){
                placeDao.insertPlace(p);
            }
            return null;
        }
    }
}
