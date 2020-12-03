package com.pizzaapp.yummypizzas.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.pizzaapp.yummypizzas.R
import com.pizzaapp.yummypizzas.Utility.Constants
import com.pizzaapp.yummypizzas.Utility.SPreference
import kotlinx.android.synthetic.main.activity_signup_screen.*

class SignupScreenActivity : AppCompatActivity() {

    private val TAG = "SignupScreenActivity"

    private lateinit var sPreference: SPreference
    private var userType: Int = 0

    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null
    private var mProgressBar: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen)
        intiUI()
    }

    private fun intiUI() {
        sPreference = SPreference(this)
        mProgressBar = ProgressDialog(this)
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("Users")
        mAuth = FirebaseAuth.getInstance()
        userType = intent.extras!!.getInt("userType", Constants.customer)
    }

    fun onBtnClick(view: View) {
        if (view.id == R.id.tvLogin) {
            finish()
        } else if (view.id == R.id.btnSignup) {
            checkValues()
        }
    }

    private fun checkValues() {
        when {
            etFullnameSignup.text.toString()
                .isEmpty() || etFullnameSignup.text.toString().length < 3 -> {
                etFullnameSignup.error = "Invalid full name"
            }
            etEmailSignup.text.toString().isEmpty() || etEmailSignup.text.toString().length < 3 -> {
                etEmailSignup.error = "Invalid email"
            }
            etPasswordSignup.text.toString()
                .isEmpty() || etPasswordSignup.text.toString().length < 3 -> {
                etPasswordSignup.error = "Invalid password"
            }
            else -> {
                mProgressBar!!.setMessage("Registering...")
                mProgressBar!!.show()
                signUpUser(
                    etFullnameSignup.text.toString(),
                    etEmailSignup.text.toString(),
                    etPasswordSignup.text.toString()
                )
            }
        }
    }

    private fun signUpUser(fullName: String, email: String, password: String) {
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                mProgressBar!!.hide()
                if (task.isSuccessful) {
                    Log.e(TAG, "createUserWithEmail:success")
                    val userId = mAuth!!.currentUser!!.uid
                    val currentUserDb = mDatabaseReference!!.child(userId)
                    currentUserDb.child("fullName").setValue(fullName)
                    openHomeScreen()
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
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