package com.pizzaapp.yummypizzas.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pizzaapp.yummypizzas.R

class EmployeeOrderTrackerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_order_tracker)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}