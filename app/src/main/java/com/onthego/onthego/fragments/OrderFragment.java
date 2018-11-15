package com.onthego.onthego.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.onthego.onthego.R;
import com.onthego.onthego.adapters.OrderAdapter;
import com.onthego.onthego.database.viewmodels.OrderViewModel;
import com.onthego.onthego.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {

    private OrderAdapter orderAdapter;
    private OrderViewModel orderViewModel;
    private List<Order> orders = new ArrayList<>();
    private TextView totalPriceTextView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.orderRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        orderAdapter = new OrderAdapter(container.getContext());
        recyclerView.setAdapter(orderAdapter);
        recyclerView.setHasFixedSize(true);

        totalPriceTextView = view.findViewById(R.id.orderTotalPriceTextView);

        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        orderViewModel.getOrders().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(@Nullable List<Order> orders) {
                setOrderList(orders);
                calculateTotalPrice();
            }
        });

        Button checkoutButton = view.findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderViewModel.deleteOrders();
                Toast.makeText(getContext().getApplicationContext(), "Compra realizada", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    public void setOrderList(List<Order> orders){
        this.orders = orders;
        orderAdapter.setOrdersList(orders);
    }

    public void calculateTotalPrice(){
        float total = 0;
        for (Order o:orders){
            total += o.getPrice();
        }
        totalPriceTextView.setText("Total: $" + total);
    }
}
