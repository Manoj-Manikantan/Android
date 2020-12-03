package com.pizzaapp.yummypizzas.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.pizzaapp.yummypizzas.R
import com.pizzaapp.yummypizzas.Utility.SPreference
import kotlinx.android.synthetic.main.activity_customer_info.*
import java.util.*

class CustomerInfoActivity : AppCompatActivity() {

    var cardType = "";
    private lateinit var sPreference: SPreference
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mProgressBar: ProgressDialog? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_info)
        intUI()
    }

    private fun intUI() {
        sPreference = SPreference(this)
        mProgressBar = ProgressDialog(this)
        mDatabase = FirebaseDatabase.getInstance()
        mAuth = FirebaseAuth.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("Users")
        if (sPreference.getBooleanValue(SPreference.isPersonalInfoFilled)) {
            bindData()
        }
    }

    private fun bindData() {
        val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference!!.child(mUser!!.uid)
        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    etName.setText(snapshot.child("fullName").value.toString())
                    etAddress.setText(snapshot.child("address").value.toString())
                    etPostalCode.setText(snapshot.child("postalCode").value.toString())
                    etPhoneNum.setText(snapshot.child("phoneNum").value.toString())
                    etCardName.setText(snapshot.child("cardName").value.toString())
                    etCardNum.setText(snapshot.child("cardNum").value.toString())
                    etExpiryMonth.setText(snapshot.child("expiryMonth").value.toString())
                    etExpYear.setText(snapshot.child("expiryYear").value.toString())
                    if (snapshot.child("cardType").value.toString() == "Debit") {
                        rbtnDebit.isChecked = true
                    } else {
                        rbtnCredit.isChecked = true
                    }
                } catch (e: Exception) {
                    Log.e("CustomerInfoActivity", "bindData: " + e.localizedMessage)
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    fun btnPayConfirmOrder(v: View) {
        if (v.id == R.id.btnPayConfirmOrder) checkInputs()
    }

    //Check if any input is empty
    private fun checkInputs() {
        when {
            TextUtils.isEmpty(etName.text) || etName.text.length < 3 -> etName.error =
                "Enter valid full name"
            TextUtils.isEmpty(etAddress.text) || etAddress.text.length < 3 -> etAddress.error =
                "Enter valid address"
            TextUtils.isEmpty(etPostalCode.text) || etPostalCode.text.length < 6 -> etPostalCode.error =
                "Enter valid postal code"
            TextUtils.isEmpty(etPhoneNum.text) || etPhoneNum.text.length < 8 -> etPhoneNum.error =
                "Enter valid phone number"
            TextUtils.isEmpty(etCardName.text) || etCardName.text.length < 3 -> etCardName.error =
                "Enter valid name on card"
            TextUtils.isEmpty(etCardNum.text) || etCardNum.text.length < 16 -> etCardNum.error =
                "Enter valid card number"
            TextUtils.isEmpty(etExpiryMonth.text) || etExpiryMonth.text.length < 2 || etExpiryMonth.text.toString()
                .toInt() > 12 -> etExpiryMonth.error =
                "Enter valid expiry month"
            TextUtils.isEmpty(etExpYear.text) || etExpYear.text.length < 4 || etExpYear.text.toString()
                .toInt() < Calendar.getInstance()
                .get(Calendar.YEAR) ->
                etExpYear.error = "Enter valid expiry year"
            else -> getCardType()
        }
    }

    private fun getCardType() {
        when (rbtngCardType.checkedRadioButtonId) {
            R.id.rbtnDebit -> cardType = "Debit"
            R.id.rbtnCredit -> cardType = "Credit"
            else -> Toast.makeText(
                applicationContext,
                "Please select type of card",
                Toast.LENGTH_SHORT
            ).show()
        }
        if (cardType.isNotEmpty()) openCheckoutActivity()
    }

    private fun openCheckoutActivity() {
        mProgressBar!!.setMessage("Saving...")
        mProgressBar!!.show()
        sPreference.setBooleanValue(SPreference.isPersonalInfoFilled, true)
        val userId = mAuth!!.currentUser!!.uid
        val currentUserDb = mDatabaseReference!!.child(userId)
        currentUserDb.child("fullName").setValue(etName.text.toString())
        currentUserDb.child("address").setValue(etAddress.text.toString())
        currentUserDb.child("phoneNum").setValue(etPhoneNum.text.toString())
        currentUserDb.child("postalCode").setValue(etPostalCode.text.toString())
        currentUserDb.child("cardName").setValue(etCardName.text.toString())
        currentUserDb.child("cardType").setValue(cardType)
        currentUserDb.child("cardNum").setValue(etCardNum.text.toString())
        currentUserDb.child("expiryMonth").setValue(etExpiryMonth.text.toString())
        currentUserDb.child("expiryYear").setValue(etExpYear.text.toString())
        mProgressBar!!.hide()
        val i = Intent(applicationContext, CustomerScreenActivity::class.java)
        Toast.makeText(
            applicationContext,
            "Personal information updated successfully.",
            Toast.LENGTH_SHORT
        ).show()
        startActivity(i)
    }
}
