package com.pizzaapp.yummypizzas.Activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pizzaapp.yummypizzas.R
import com.pizzaapp.yummypizzas.Room.Database.PizzaDatabase

class CustomerOrderSummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_order_summary)
        initUI()
    }

    private fun initUI() {
        val roomDatabase = PizzaDatabase.getDatabase(this)
        val allPizza = roomDatabase.pizzaDAO().getAllProducts()
        for (pizza in allPizza) {
            Log.e("Order Details", "getAllPizza: pizza.pizzaType: " + pizza.pizzaType)
        }
    }
}
