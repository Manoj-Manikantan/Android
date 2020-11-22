package com.pizzaapp.yummypizzas.Room.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pizzaapp.yummypizzas.Room.Entity.Customer

@Dao
interface CustomerDAO {
    @Query("SELECT * FROM customer_table")
    fun getAllCustomers(): List<Customer>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCustomer(customer: Customer)

    @Query("DELETE FROM customer_table")
    suspend fun deleteAllCustomers()
}