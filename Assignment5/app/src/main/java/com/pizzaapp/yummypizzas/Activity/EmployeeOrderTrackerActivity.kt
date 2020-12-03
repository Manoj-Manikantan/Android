package com.pizzaapp.yummypizzas.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.pizzaapp.yummypizzas.Adapter.OrderListAdapter
import com.pizzaapp.yummypizzas.R
import com.pizzaapp.yummypizzas.Room.Database.PizzaDatabase
import com.pizzaapp.yummypizzas.Room.Entity.Order
import com.pizzaapp.yummypizzas.Utility.Constants
import com.pizzaapp.yummypizzas.Utility.SPreference
import kotlinx.android.synthetic.main.activity_customer_screen.*
import kotlinx.android.synthetic.main.activity_employee_order_tracker.*

class EmployeeOrderTrackerActivity : AppCompatActivity(), OrderListAdapter.OrderClickListener {
    private lateinit var sPreference: SPreference
    private lateinit var orderListAdapter: OrderListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_order_tracker)
        initUI()
    }

    private fun initUI() {
        sPreference = SPreference(this)
        tvEmployeeName.text = "Hello " + sPreference.getStringValue(SPreference.userName)
        val roomDatabase = PizzaDatabase.getDatabase(this)
        val allOrders = roomDatabase.orderDAO().getAllOrders() as ArrayList<Order>
        if (allOrders.isNotEmpty()) {
            orderListAdapter = OrderListAdapter(allOrders, this, this)
        }
        val linearLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvOrderList.layoutManager = linearLayout
        rvOrderList.adapter = orderListAdapter
    }

    fun onButtonPressed(view: View) {
        if (view.id == R.id.btnLogout) {
            sPreference.setBooleanValue(SPreference.isLogin,false)
            sPreference.setStringValue(SPreference.userName,"")
            sPreference.setStringValue(SPreference.password,"")
            Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainScreenActivity::class.java))
            finish()
        }
    }

    override fun onStateChanged(item: Order, position: Int) {
        if (item.orderStatus == Constants.inProgress) {
            item.orderStatus = Constants.completed
        } else {
            item.orderStatus = Constants.inProgress
        }
        val roomDatabase = PizzaDatabase.getDatabase(this)
        roomDatabase.orderDAO().updateOrder(item)
        orderListAdapter.notifyItemChanged(position)
    }
}