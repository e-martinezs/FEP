package com.onthego.onthego.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.onthego.onthego.R;
import com.onthego.onthego.fragments.FoodListFragment;
import com.onthego.onthego.models.Place;
import com.onthego.onthego.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>{

    private Context context;
    private List<Place> places = new ArrayList<>();

    public PlaceAdapter(Context context){
        this.context = context;
    }

    public void setPlaces(List<Place> places){
        this.places = places;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_place, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        final Place place = places.get(position);
        holder.nameTextView.setText(place.getName());
        ImageLoader.loadImage(place.getImage(), holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                FoodListFragment fragment = new FoodListFragment();
                fragment.setPlace(place.getName());
                ((FragmentActivity)view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView nameTextView;
        private ImageView imageView;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.placeCardview);
            nameTextView = itemView.findViewById(R.id.placeNameTextView);
            imageView = itemView.findViewById(R.id.placeImageView);
        }
    }
}
