package com.pizzaapp.yummypizzas.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.pizzaapp.yummypizzas.R
import com.pizzaapp.yummypizzas.Utility.Constants
import com.pizzaapp.yummypizzas.Utility.SPreference
import kotlinx.android.synthetic.main.activity_login_screen.*
import kotlinx.android.synthetic.main.activity_login_screen.view.*

class LoginScreenActivity : AppCompatActivity() {

    private lateinit var sPreference: SPreference
    private var userType: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        intiUI()
    }

    private fun intiUI() {
        sPreference = SPreference(this)
        userType = intent.extras!!.getInt("userType", Constants.customer)

        if (userType == Constants.customer) {
            sPreference.setStringValue(SPreference.defaultUserName, Constants.customerUsername)
            sPreference.setStringValue(SPreference.defaultPassword, Constants.customerPassword)
        } else if (userType == Constants.employee) {
            sPreference.setStringValue(SPreference.defaultUserName, Constants.employeeUsername)
            sPreference.setStringValue(SPreference.defaultPassword, Constants.employeePassword)
        }

    }

    fun onBtnLoginClick(view: View) {
        if (view.id == R.id.btnLogin) {
            checkValues()
        }
    }

    private fun checkValues() {
        when {
            etUsername.text.toString().isEmpty() || etUsername.text.toString().length < 3 -> {
                etUsername.error = "Invalid username"
            }
            etPassword.text.toString().isEmpty() || etPassword.text.toString().length < 3 -> {
                etPassword.error = "Invalid password"
            }
            else -> {
                checkLogin(etUsername.text.toString(), etPassword.text.toString())
            }
        }
    }

    private fun checkLogin(userName: String, password: String) {
        when {
            sPreference.getStringValue(SPreference.defaultUserName) != userName -> {
                etUsername.error = "Incorrect username"
            }
            sPreference.getStringValue(SPreference.defaultPassword) != password -> {
                etUsername.error = "Incorrect password"
            }
            else -> {
                openHomeScreen()
            }
        }
    }

    private fun openHomeScreen() {
        sPreference.setBooleanValue(SPreference.isLogin, true)
        sPreference.setIntValue(SPreference.userType, userType)
        val intent = Intent(this, MainScreenActivity::class.java)
        when (userType) {
            Constants.customer -> {
                Intent(this, CustomerScreenActivity::class.java)
            }
            Constants.employee -> {
                Intent(this, EmployeeOrderTrackerActivity::class.java)
            }
        }
        startActivity(intent)
        finish()
    }
}