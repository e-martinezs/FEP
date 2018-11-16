package com.onthego.onthego.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.onthego.onthego.R;
import com.onthego.onthego.database.viewmodels.OrderViewModel;
import com.onthego.onthego.models.Food;
import com.onthego.onthego.models.Order;
import com.onthego.onthego.utils.ImageLoader;

import java.text.DecimalFormat;

public class FoodDetailFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_detail, container, false);
        final Food food = (Food)getArguments().getSerializable("FOOD");
        TextView nameTextView = view.findViewById(R.id.foodDetailNameTextView);
        TextView priceTextView = view.findViewById(R.id.foodDetailPriceTextView);
        TextView descriptionTextView = view.findViewById(R.id.foodDetailDescriptionTextView);
        ImageView imageView = view.findViewById(R.id.foodDetailImageView);
        ImageButton buyButton = view.findViewById(R.id.foodDetailBuyButton);

        nameTextView.setText(food.getName());
        DecimalFormat df = new DecimalFormat("0.00##");
        priceTextView.setText("$"+df.format(food.getPrice()));
        descriptionTextView.setText(food.getDescription());
        ImageLoader.loadImage(food.getImage(), imageView);

        final OrderViewModel orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

        buyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Order order = new Order();
                order.setName(food.getName());
                order.setFoodId(food.getId());
                order.setPrice(food.getPrice());
                order.setQuantity(1);
                order.setPlaceId(food.getPlaceId());
                orderViewModel.insertOrder(order);
                Toast.makeText(getContext().getApplicationContext(), "Producto agregado a la orden", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
