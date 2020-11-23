package com.pizzaapp.yummypizzas.Room.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pizzaapp.yummypizzas.Room.Entity.Order

@Dao
interface OrderDAO {

    @Query("SELECT * FROM order_table")
    fun getAllOrders(): List<Order>

    @Query("SELECT * FROM order_table order by orderId desc limit 1")
    fun getLastOrder(): Order

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrder(order: Order)

    @Query("DELETE FROM order_table")
    fun deleteAllOrders()

}