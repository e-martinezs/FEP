package com.onthego.onthego.database.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.onthego.onthego.database.repositories.OrderRepository;
import com.onthego.onthego.models.Order;

import java.util.List;

public class OrderViewModel extends AndroidViewModel{

    private OrderRepository orderRepository;
    private LiveData<List<Order>> orders;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        orderRepository = new OrderRepository(application);
        orders = orderRepository.getOrders();
    }

    public LiveData<List<Order>> getOrders() {
        return orders;
    }

    public void insertOrder(Order order){
        orderRepository.insertOrder(order);
    }

    public void deleteOrders(){
        orderRepository.deleteOrders();
    }
}
