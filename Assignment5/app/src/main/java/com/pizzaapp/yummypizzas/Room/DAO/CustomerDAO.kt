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

    @Query(
        "SELECT * FROM customer_table where userName == :username")
    fun getCustomerByUsername(username: String): Customer

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCustomer(customer: Customer)

    @Query("DELETE FROM customer_table")
    suspend fun deleteAllCustomers()
}