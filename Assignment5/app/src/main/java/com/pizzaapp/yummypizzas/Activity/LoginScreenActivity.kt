package com.pizzaapp.yummypizzas.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.pizzaapp.yummypizzas.R
import com.pizzaapp.yummypizzas.Utility.Constants
import com.pizzaapp.yummypizzas.Utility.SPreference
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginScreenActivity : AppCompatActivity() {
    private val TAG = "LoginScreenActivity"

    private lateinit var sPreference: SPreference
    private var userType: Int = 0
    private var mAuth: FirebaseAuth? = null
    private var mProgressBar: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        intiUI()
    }

    private fun intiUI() {
        sPreference = SPreference(this)
        userType = intent.extras!!.getInt("userType", Constants.customer)
        mProgressBar = ProgressDialog(this)
        mAuth = FirebaseAuth.getInstance()
    }

    fun onBtnClick(view: View) {
        if (view.id == R.id.btnLogin) {
            checkValues()
        } else if (view.id == R.id.tvSignup) {
            val intent = Intent(this, SignupScreenActivity::class.java)
            intent.putExtra("userType", userType)
            startActivity(intent)
        }
    }

    private fun checkValues() {
        when {
            etEmailLogin.text.toString().isEmpty() || etEmailLogin.text.toString().length < 3 -> {
                etEmailLogin.error = "Invalid email"
            }
            etPasswordLogin.text.toString()
                .isEmpty() || etPasswordLogin.text.toString().length < 3 -> {
                etPasswordLogin.error = "Invalid password"
            }
            else -> {
                mProgressBar!!.setMessage("Logging in...")
                mProgressBar!!.show()
                checkLogin(etEmailLogin.text.toString(), etPasswordLogin.text.toString())
            }
        }
    }

    private fun checkLogin(email: String, password: String) {
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                mProgressBar!!.hide()
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    openHomeScreen()
                } else {
                    Log.e(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun openHomeScreen() {
        sPreference.setBooleanValue(SPreference.isLogin, true)
        sPreference.setIntValue(SPreference.userType, userType)
        val intent: Intent = when (userType) {
            Constants.customer -> {
                Intent(this, CustomerScreenActivity::class.java)
            }
            Constants.employee -> {
                Intent(this, EmployeeOrderTrackerActivity::class.java)
            }
            else -> {
                Intent(this, MainScreenActivity::class.java)
            }
        }
        startActivity(intent)
        finish()
    }
}