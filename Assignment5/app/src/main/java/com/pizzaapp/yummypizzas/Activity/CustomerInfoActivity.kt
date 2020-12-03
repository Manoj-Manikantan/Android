package com.pizzaapp.yummypizzas.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pizzaapp.yummypizzas.R
import com.pizzaapp.yummypizzas.Room.Database.PizzaDatabase
import com.pizzaapp.yummypizzas.Room.Entity.Customer
import com.pizzaapp.yummypizzas.Utility.SPreference
import kotlinx.android.synthetic.main.activity_customer_info.*
import java.lang.Exception
import java.util.*


class CustomerInfoActivity : AppCompatActivity() {

    var cardType = "";
    private lateinit var sPreference: SPreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_info)
        intUI()
    }

    private fun intUI() {
        sPreference = SPreference(this)
        if (sPreference.getBooleanValue(SPreference.isPersonalInfoFilled)) {
            val roomDatabase = PizzaDatabase.getDatabase(this)
            val currentCustomer = roomDatabase.customerDAO()
                .getCustomerByUsername(sPreference.getStringValue(SPreference.userName))
            bindData(currentCustomer)
        }
    }

    private fun bindData(currentCustomer: Customer) {
        try {
            etName.setText(currentCustomer.fullName)
            etAddress.setText(currentCustomer.address)
            etPostalCode.setText(currentCustomer.postalCode)
            etPhoneNum.setText(currentCustomer.phoneNum)
            etCardName.setText(currentCustomer.cardName)
            etCardNum.setText(currentCustomer.cardNumber)
            etExpiryMonth.setText("" + currentCustomer.expiryMonth)
            etExpYear.setText("" + currentCustomer.expiryYear)
            if (currentCustomer.cardType == "Debit") {
                rbtnDebit.isChecked = true
            } else {
                rbtnCredit.isChecked = true
            }
        } catch (e: Exception) {
            Log.e("CustomerInfoActivity", "bindData: " + e.localizedMessage )
        }

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
        sPreference.setBooleanValue(SPreference.isPersonalInfoFilled, true)

        val newCustomer = Customer(
            sPreference.getStringValue(SPreference.userName),
            sPreference.getStringValue(SPreference.password),
            etName.text.toString(),
            etAddress.text.toString(),
            etPhoneNum.text.toString(),
            etPostalCode.text.toString(),
            etCardName.text.toString(),
            cardType,
            etCardNum.text.toString(),
            etExpiryMonth.text.toString().toInt(),
            etExpYear.text.toString().toInt()
        )
        val newThread = saveCustomerDetails(newCustomer, this)
        newThread.start()
        val i = Intent(applicationContext, CustomerScreenActivity::class.java)
        Toast.makeText(
            applicationContext,
            "Personal information updated successfully.",
            Toast.LENGTH_SHORT
        ).show()
        startActivity(i)
    }

}

class saveCustomerDetails() : Thread() {
    private lateinit var customer: Customer
    private lateinit var context: Context

    constructor(customer: Customer, context: Context) : this() {
        this.customer = customer
        this.context = context
    }

    override fun run() {
        val roomDatabase = PizzaDatabase.getDatabase(context)
        roomDatabase.customerDAO().insertCustomer(customer)
    }
}