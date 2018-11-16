package com.onthego.onthego.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onthego.onthego.R;
import com.onthego.onthego.models.Order;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    private List<Order> orders = new ArrayList<>();
    private Context context;

    public OrderAdapter(Context context){
        this.context = context;
    }

    public void setOrdersList(List<Order> orders){
        this.orders = orders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_order, parent, false);
        return new OrderAdapter.OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.nameTextView.setText(order.getName());
        DecimalFormat df = new DecimalFormat("0.00##");
        holder.priceTextView.setText("$"+df.format(order.getPrice()));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTextView;
        private TextView priceTextView;

        public OrderViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.orderNameTextView);
            priceTextView = itemView.findViewById(R.id.orderPriceTextView);
        }
    }
}
