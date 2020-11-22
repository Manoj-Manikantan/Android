package com.pizzaapp.yummypizzas.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.pizzaapp.yummypizzas.R
import com.pizzaapp.yummypizzas.Utility.Constants
import com.pizzaapp.yummypizzas.Utility.SPreference

class MainScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
    }

    fun onButtonClick(view: View) {
        when (view.id) {
            R.id.btnUserLogin -> openLoginScreen(Constants.customer)
            R.id.btnEmployee -> openLoginScreen(Constants.employee)
        }
    }

    private fun openLoginScreen(userType: Int) {
        val intent = Intent(this, LoginScreenActivity::class.java)
        intent.putExtra("userType", userType)
        startActivity(intent)
        finish()
    }
}