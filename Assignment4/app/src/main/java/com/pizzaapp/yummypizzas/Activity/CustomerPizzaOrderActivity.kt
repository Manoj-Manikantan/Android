package com.pizzaapp.yummypizzas.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.pizzaapp.yummypizzas.R
import com.pizzaapp.yummypizzas.Room.Database.PizzaDatabase
import com.pizzaapp.yummypizzas.Room.Entity.Pizza
import kotlinx.android.synthetic.main.activity_customer_pizza_order.*

class CustomerPizzaOrderActivity : AppCompatActivity() {

    var pizzaName: String = ""
    var pizzaSize: String = ""
    var extraToppings = arrayOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_pizza_order)
        initUI()
    }

    private fun initUI() {

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
    }

    //On Click of Place Order button
    fun btnPizzaOrder(v: View) {
        if (v.id == R.id.btnConfirmOrder) getPizzaSize()
    }

    //Handling radio buttons for pizza size
    private fun getPizzaSize() {
        when (radioBtnGroup.checkedRadioButtonId) {
            R.id.rdButtonLarge -> pizzaSize = "Large"
            R.id.rdButtonMedium -> pizzaSize = "Medium"
            R.id.rdButtonSmall -> pizzaSize = "Small"
            else -> Toast.makeText(
                applicationContext,
                "Please select size of your pizza",
                Toast.LENGTH_SHORT
            ).show()
        }
        if (pizzaSize.isNotEmpty()) getToppings()
    }

    //Handling checkbox for toppings
    private fun getToppings() {
        if (chBoxCheese.isChecked) extraToppings += "Cheese"
        if (chBoxGP.isChecked) extraToppings += "Green Pepper"
        if (chBoxSH.isChecked) extraToppings += "Smoked Ham"
        if (chBoxBO.isChecked) extraToppings += "Black Olives"
        if (chBoxSpinach.isChecked) extraToppings += "Spinach"
        if (chBoxSO.isChecked) extraToppings += "Spanish Onions"
        openCustomerInfoActivity()
    }

    //Open Checkout activity
    private fun openCustomerInfoActivity() {
        val pizza = Pizza(pizzaName, pizzaSize , extraToppings.toString())
        val t1 = addPizzaThread(pizza, this)
        t1.start()
        val i = Intent(applicationContext, CustomerScreenActivity::class.java)
        Toast.makeText(
            applicationContext,
            "Order placed successfully",
            Toast.LENGTH_SHORT
        ).show()
        i.putExtra("pizzaName", pizzaName)
        i.putExtra("pizzaSize", pizzaSize)
        i.putExtra("extraToppings", extraToppings)
        startActivity(i)
    }
}


class addPizzaThread() : Thread() {
    private lateinit var pizza: Pizza
    private lateinit var context: Context

    constructor(pizza: Pizza, context: Context) : this() {
        this.pizza = pizza
        this.context = context
    }

    override fun run() {
        val roomDatabase = PizzaDatabase.getDatabase(context)
        roomDatabase.pizzaDAO().insertProduct(pizza)
    }
}