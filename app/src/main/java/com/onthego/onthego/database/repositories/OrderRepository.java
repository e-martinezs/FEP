package com.onthego.onthego.database.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.onthego.onthego.database.OnTheGoDatabase;
import com.onthego.onthego.database.daos.OrderDao;
import com.onthego.onthego.models.Order;

import java.util.List;

public class OrderRepository {
    private OrderDao orderDao;
    private LiveData<List<Order>> orders;

    public OrderRepository(Application application){
        OnTheGoDatabase db = OnTheGoDatabase.getDatabase(application);
        orderDao = db.orderDao();
        orders = orderDao.getOrders();
    }

    public LiveData<List<Order>> getOrders() {
        return orders;
    }

    public void deleteOrders(){
        new OrderRepository.deleteOrdersAsyncTask(orderDao).execute();
    }

    public void insertOrder(Order order){
        new OrderRepository.insertOrderAsyncTask(orderDao).execute(order);
    }

    private static class insertOrderAsyncTask extends AsyncTask<Order, Void, Void> {
        private OrderDao orderDao;

        public insertOrderAsyncTask(OrderDao orderDao){
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.insertOrder(orders[0]);
            return null;
        }
    }

    private static class deleteOrdersAsyncTask extends AsyncTask<Void, Void, Void>{
        private OrderDao orderDao;

        public deleteOrdersAsyncTask(OrderDao orderDao){
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            orderDao.deleteOrders();
            return null;
        }
    }
}
