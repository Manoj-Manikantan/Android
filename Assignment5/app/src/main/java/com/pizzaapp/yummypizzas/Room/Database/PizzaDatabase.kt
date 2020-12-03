package com.pizzaapp.yummypizzas.Room.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pizzaapp.yummypizzas.Room.DAO.CustomerDAO
import com.pizzaapp.yummypizzas.Room.DAO.EmployeeDAO
import com.pizzaapp.yummypizzas.Room.DAO.OrderDAO
import com.pizzaapp.yummypizzas.Room.DAO.PizzaDAO
import com.pizzaapp.yummypizzas.Room.Entity.Customer
import com.pizzaapp.yummypizzas.Room.Entity.Employee
import com.pizzaapp.yummypizzas.Room.Entity.Order
import com.pizzaapp.yummypizzas.Room.Entity.Pizza

@Database(entities = [Employee::class, Customer::class, Order::class, Pizza::class], version = 1, exportSchema = false)
public abstract class PizzaDatabase : RoomDatabase() {

    abstract fun employeeDAO(): EmployeeDAO
    abstract fun customerDAO(): CustomerDAO
    abstract fun pizzaDAO(): PizzaDAO
    abstract fun orderDAO(): OrderDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PizzaDatabase? = null

        fun getDatabase(context: Context): PizzaDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PizzaDatabase::class.java,
                    "pizzaApp.db"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}