package com.pizzaapp.yummypizzas.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pizzaapp.yummypizzas.Adapter.OrderListAdapter
import com.pizzaapp.yummypizzas.R
import com.pizzaapp.yummypizzas.Room.Database.PizzaDatabase
import com.pizzaapp.yummypizzas.Room.Entity.Order
import com.pizzaapp.yummypizzas.Utility.SPreference
import kotlinx.android.synthetic.main.activity_customer_screen.*


class CustomerScreenActivity : AppCompatActivity() {
    private lateinit var sPreference: SPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_screen)
        initUI()
    }

    private fun initUI() {
        sPreference = SPreference(this)
        tvUserName.text = "Hello " + sPreference.getStringValue(SPreference.userName)
    }

    fun onButtonPressed(view: View) {
        if (view.id == R.id.btnLogout) {
            sPreference.setBooleanValue(SPreference.isLogin, false)
            sPreference.setStringValue(SPreference.userName, "")
            sPreference.setStringValue(SPreference.password, "")
            Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainScreenActivity::class.java))
            finish()
        } else if (view.id == R.id.btnPlaceOrder) {
            if (sPreference.getBooleanValue(SPreference.isPersonalInfoFilled)) {
                startActivity(Intent(this, CustomerPizzaOrderActivity::class.java))
            } else {
                Toast.makeText(
                    this,
                    "Please fill all the fields in Personal information to place an order",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, CustomerInfoActivity::class.java))
            }
        } else if (view.id == R.id.btnPersonalInfo) {
            startActivity(Intent(this, CustomerInfoActivity::class.java))
        } else if (view.id == R.id.btnOrderDetails) {

            if (sPreference.getBooleanValue(SPreference.isPersonalInfoFilled)) {
                val roomDatabase = PizzaDatabase.getDatabase(this)
                val allOrders = roomDatabase.orderDAO().getAllOrders() as ArrayList<Order>
                if (allOrders.isNotEmpty()) {
                    startActivity(Intent(this, CustomerOrderSummaryActivity::class.java))
                } else {
                    Toast.makeText(
                        this,
                        "Please place your order to view the status",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this, CustomerPizzaOrderActivity::class.java))
                }
            } else {
                Toast.makeText(
                    this,
                    "Please fill all the fields in Personal information to place an order",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, CustomerInfoActivity::class.java))
            }


        }
    }
}