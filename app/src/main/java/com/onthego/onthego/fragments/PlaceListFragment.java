package com.onthego.onthego.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onthego.onthego.R;
import com.onthego.onthego.adapters.PlaceAdapter;
import com.onthego.onthego.api.APIRequest;
import com.onthego.onthego.database.viewmodels.PlaceViewModel;
import com.onthego.onthego.models.Place;

import java.util.ArrayList;
import java.util.List;

public class PlaceListFragment extends Fragment{

    private PlaceAdapter placeAdapter;
    private PlaceViewModel placeViewModel;
    private List<Place> places = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(container.getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        placeAdapter = new PlaceAdapter(container.getContext());
        recyclerView.setAdapter(placeAdapter);
        recyclerView.setHasFixedSize(true);

        placeViewModel = ViewModelProviders.of(this).get(PlaceViewModel.class);
        placeViewModel.getPlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(@Nullable List<Place> places) {
                for (Place p:places){
                    System.out.println(p.getName());
                }
                setPlacesList(places);
            }
        });

        return view;
    }

    public void setPlacesList(List<Place> places){
        this.places = places;
        placeAdapter.setPlaces(places);
    }
}
