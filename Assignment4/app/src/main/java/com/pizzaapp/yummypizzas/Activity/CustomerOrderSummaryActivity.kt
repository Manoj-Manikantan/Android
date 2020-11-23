package com.pizzaapp.yummypizzas.Activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pizzaapp.yummypizzas.R
import com.pizzaapp.yummypizzas.Room.Database.PizzaDatabase
import com.pizzaapp.yummypizzas.Room.Entity.Order
import kotlinx.android.synthetic.main.activity_customer_order_summary.*

class CustomerOrderSummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_order_summary)
        initUI()
    }

    private fun initUI() {
        val roomDatabase = PizzaDatabase.getDatabase(this)
        val lastOrder = roomDatabase.orderDAO().getLastOrder()
        textViewOrderDate.text = "Order Date : " + lastOrder.orderDate
        textViewCustomerName.text = "Customer Name : " + lastOrder.customerName
        textViewPizzaName.text = "Pizza Name : " + lastOrder.pizzaName
        textViewPizzaSizeQty.text =
            "Size : " + lastOrder.pizzaSize + " , Quantity : " + lastOrder.quantity
        textViewTotalPrice.text = "Total Price : $" + lastOrder.price
        Log.e("Order Details", "getAllPizza: lastOrder.price: " + lastOrder.price)
    }


}
