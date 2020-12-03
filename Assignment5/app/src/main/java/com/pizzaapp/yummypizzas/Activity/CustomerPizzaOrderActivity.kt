package com.pizzaapp.yummypizzas.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.pizzaapp.yummypizzas.R
import com.pizzaapp.yummypizzas.Room.Database.PizzaDatabase
import com.pizzaapp.yummypizzas.Room.Entity.Order
import com.pizzaapp.yummypizzas.Utility.Constants
import com.pizzaapp.yummypizzas.Utility.SPreference
import kotlinx.android.synthetic.main.activity_customer_pizza_order.*
import java.time.LocalDateTime

class CustomerPizzaOrderActivity : AppCompatActivity() {

    var pizzaName: String = ""
    var pizzaSize: String = ""
    var pizzaQuantity: Int = 1
    var totalPrize: Int = 0

    private lateinit var sPreference: SPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_pizza_order)
        initUI()
    }

    private fun initUI() {
        sPreference = SPreference(this)
        val pizzaList = resources.getStringArray(R.array.myPizzaList)
        val adapter = ArrayAdapter(applicationContext, R.layout.item_spinner, pizzaList)

        //Handling Spinner
        spinnerPizzaList.adapter = adapter
        spinnerPizzaList.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                pizzaName = pizzaList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // pizzaName value remains empty
            }
        }

        radioBtnGroup.setOnCheckedChangeListener { group, checkedId ->
            run {
                when (checkedId) {
                    R.id.rdButtonLarge -> pizzaSize = "Large"
                    R.id.rdButtonMedium -> pizzaSize = "Medium"
                    R.id.rdButtonSmall -> pizzaSize = "Small"
                }
                calculatePrice()
            }
        }


        etQuantity.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s!!.isNotEmpty()) {
                    pizzaQuantity = etQuantity.text.toString().toInt()
                    calculatePrice()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //beforeTextChanged
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //onTextChanged
            }
        })


    }

    private fun calculatePrice() {
        if (pizzaSize.isNotEmpty()) {
            var priceForOne = 0
            when (pizzaSize) {
                "Large" -> priceForOne = 20
                "Medium" -> priceForOne = 15
                "Small" -> priceForOne = 10
            }
            totalPrize = priceForOne * pizzaQuantity
            tvTotalAmount.text = "$ $totalPrize"
        }

    }

    //On Click of Place Order button
    @SuppressLint("NewApi")
    fun btnPizzaOrder(v: View) {
        if (v.id == R.id.btnConfirmOrder) getQuantity()
    }

    //Handling radio buttons for pizza size
    @SuppressLint("NewApi")
    private fun getPizzaSize() {
        if (pizzaSize.isNotEmpty()) getQuantity()
    }

    //Handling checkbox for toppings
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getQuantity() {
        if (etQuantity.text.toString().isEmpty()) {
            etQuantity.error = "Enter quantity"
        } else {
            pizzaQuantity = etQuantity.text.toString().toInt()
            openCustomerInfoActivity()
        }

    }

    //Open Checkout activity
    @RequiresApi(Build.VERSION_CODES.O)
    private fun openCustomerInfoActivity() {
        val newOrder = Order(
            sPreference.getStringValue(SPreference.userName),
            pizzaName,
            pizzaSize,
            pizzaQuantity,
            totalPrize,
            LocalDateTime.now().toString(),
            Constants.inProgress
        )
        val t1 = addPizzaThread(newOrder, this)
        t1.start()
        val i = Intent(applicationContext, CustomerScreenActivity::class.java)
        Toast.makeText(
            applicationContext,
            "Order placed successfully",
            Toast.LENGTH_SHORT
        ).show()
        startActivity(i)
    }
}


class addPizzaThread() : Thread() {
    private lateinit var order: Order
    private lateinit var context: Context

    constructor(order: Order, context: Context) : this() {
        this.order = order
        this.context = context
    }

    override fun run() {
        val roomDatabase = PizzaDatabase.getDatabase(context)
        roomDatabase.orderDAO().insertOrder(order)
    }
}