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
import com.onthego.onthego.adapters.FoodAdapter;
import com.onthego.onthego.adapters.PlaceAdapter;
import com.onthego.onthego.api.APIRequest;
import com.onthego.onthego.database.viewmodels.FoodViewModel;
import com.onthego.onthego.database.viewmodels.PlaceViewModel;
import com.onthego.onthego.models.Food;
import com.onthego.onthego.models.Place;

import java.util.ArrayList;
import java.util.List;

public class FoodListFragment extends Fragment{

    private FoodAdapter foodAdapter;
    private FoodViewModel foodViewModel;
    private String place;
    private List<Food> foods = new ArrayList<>();

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
        foodAdapter = new FoodAdapter(container.getContext());
        recyclerView.setAdapter(foodAdapter);
        recyclerView.setHasFixedSize(true);

        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        foodViewModel.getFoods(place).observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(@Nullable List<Food> foods) {
                for (Food f:foods){
                    System.out.println(f.getName());
                }
                setFoodsList(foods);
            }
        });

        return view;
    }

    public void setPlace(String place){
        this.place = place;
    }

    public void setFoodsList(List<Food> foods){
        this.foods = foods;
        foodAdapter.setFoods(foods);
    }
}
