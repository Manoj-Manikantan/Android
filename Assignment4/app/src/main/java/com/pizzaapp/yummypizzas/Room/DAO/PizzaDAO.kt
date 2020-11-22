package com.pizzaapp.yummypizzas.Room.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pizzaapp.yummypizzas.Room.Entity.Pizza

@Dao
interface PizzaDAO {

    @Query("SELECT * FROM pizza_table")
    fun getAllProducts(): List<Pizza>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProduct(pizza: Pizza)

    @Query("DELETE FROM pizza_table")
    fun deleteAllProducts()

}