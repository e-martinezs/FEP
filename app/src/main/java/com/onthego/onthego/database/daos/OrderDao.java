package com.onthego.onthego.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.onthego.onthego.models.Order;

import java.util.List;

@Dao
public interface OrderDao {

    @Query("SELECT * FROM order_table")
    LiveData<List<Order>> getOrders();

    @Query("DELETE FROM order_table")
    void deleteOrders();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrder(Order order);
}
