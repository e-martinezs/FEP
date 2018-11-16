package com.onthego.onthego.adapters;

import android.content.Context;
import android.os.Bundle;
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
import com.onthego.onthego.fragments.FoodDetailFragment;
import com.onthego.onthego.models.Food;
import com.onthego.onthego.utils.ImageLoader;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{

    private Context context;
    private List<Food> foods = new ArrayList<>();

    public FoodAdapter(Context context){
        this.context = context;
    }

    public void setFoods(List<Food> foods){
        this.foods = foods;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_food, parent, false);
        return new FoodAdapter.FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.FoodViewHolder holder, int position) {
        final Food food = foods.get(position);
        holder.nameTextView.setText(food.getName());
        DecimalFormat df = new DecimalFormat("0.00##");
        holder.priceTextView.setText("$"+df.format(food.getPrice()));
        ImageLoader.loadImage(food.getImage(), holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                FoodDetailFragment fragment = new FoodDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("FOOD", food);
                fragment.setArguments(bundle);
                ((FragmentActivity)view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView nameTextView;
        private TextView priceTextView;
        private ImageView imageView;

        public FoodViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.foodCardview);
            nameTextView = itemView.findViewById(R.id.foodNameTextView);
            priceTextView = itemView.findViewById(R.id.foodPriceTextView);
            imageView = itemView.findViewById(R.id.foodImageView);
        }
    }
}
