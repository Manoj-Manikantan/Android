package com.pizzaapp.yummypizzas.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.pizzaapp.yummypizzas.R
import com.pizzaapp.yummypizzas.Utility.Constants
import com.pizzaapp.yummypizzas.Utility.SPreference

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var sPreference: SPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initUI()
    }

    private fun initUI() {
        sPreference = SPreference(this)
        Handler(Looper.getMainLooper()).postDelayed({
            run {
                var intent = Intent(this, MainScreenActivity::class.java)
                if (sPreference.getBooleanValue(SPreference.isLogin)) {
                    intent = when {
                        sPreference.getIntValue(SPreference.userType) == Constants.customer -> {
                            Intent(this, CustomerScreenActivity::class.java)
                        }
                        sPreference.getIntValue(SPreference.userType) == Constants.employee -> {
                            Intent(this, EmployeeOrderTrackerActivity::class.java)
                        }
                        else -> {
                            Intent(this, MainScreenActivity::class.java)
                        }
                    }
                }
                startActivity(intent)
                finish()
            }
        }, 2000)
    }
}