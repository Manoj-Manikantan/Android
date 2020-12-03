package com.pizzaapp.yummypizzas.Room.DAO

import androidx.room.*
import com.pizzaapp.yummypizzas.Room.Entity.Order

@Dao
interface OrderDAO {

    @Query("SELECT * FROM order_table")
    fun getAllOrders(): List<Order>

    @Query("SELECT * FROM order_table order by orderId desc limit 1")
    fun getLastOrder(): Order

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrder(order: Order)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateOrder(order: Order)

    @Query("DELETE FROM order_table")
    fun deleteAllOrders()

}