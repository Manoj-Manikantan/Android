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
        val t1 = getAllPizza(this)
        t1.start()
    }
}


class getAllPizza() : Thread() {

    private lateinit var context: Context

    constructor(context: Context) : this() {
        this.context = context
    }

    override fun run() {
        val roomDatabase = PizzaDatabase.getDatabase(context)
        val allPizza = roomDatabase.pizzaDAO().getAllProducts()
        for (pizza in allPizza) {
            Log.e("Order Details", "getAllPizza: pizza.pizzaType: " + pizza.pizzaType)
        }
    }
}