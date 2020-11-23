package com.pizzaapp.yummypizzas.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pizzaapp.yummypizzas.R

class activity_list_item : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.row_order_item)
    }
}