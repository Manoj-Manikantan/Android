package com.pizzaapp.yummypizzas.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.pizzaapp.yummypizzas.R
import com.pizzaapp.yummypizzas.Utility.Constants
import com.pizzaapp.yummypizzas.Utility.SPreference
import kotlinx.android.synthetic.main.activity_customer_pizza_order.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import java.time.LocalDateTime


class CustomerPizzaOrderActivity : AppCompatActivity() {

    var pizzaName: String = ""
    var pizzaSize: String = ""
    var pizzaQuantity: Int = 1
    var totalPrice: Int = 0

    private lateinit var sPreference: SPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_pizza_order)
        initUI()
    }

    private fun initUI() {
        sPreference = SPreference(this)

        getPizzaNames()

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

    private fun getPizzaNames() {
        val url =
            "https://gist.githubusercontent.com/Jn1532/bada808b6f3f146dbaf268512655a964/raw/f657f97ca93be3a618d09d3d4bffdb470b8514f8/toppings.json"
        val request = Request.Builder()
            .url(url)
            .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                val jsonArr = JSONArray(response.body()?.string())
                val pizzaList: ArrayList<String> = ArrayList()
                (0..10).forEach { index ->
                    val jsonObj = jsonArr.getJSONObject(index)
                    if (jsonObj.has("toppings")) {
                        val toppingsArr = jsonObj.getJSONArray("toppings")
                        val pizzaName = capitalizeString(toppingsArr[0] as String)
                        if (!pizzaList.contains(pizzaName)) {
                            pizzaList.add(pizzaName)
                        }
                    }
                }

                runOnUiThread {
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
            }
        })
    }

    fun capitalizeString(str: String): String {
        var retStr = str
        try {
            retStr = str.substring(0, 1).toUpperCase() + str.substring(1)
        } catch (e: Exception) {
        }
        return retStr
    }

    private fun calculatePrice() {
        if (pizzaSize.isNotEmpty()) {
            var priceForOne = 0
            when (pizzaSize) {
                "Large" -> priceForOne = 20
                "Medium" -> priceForOne = 15
                "Small" -> priceForOne = 10
            }
            totalPrice = priceForOne * pizzaQuantity
            tvTotalAmount.text = "$ $totalPrice"
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
        val myDB = FirebaseFirestore.getInstance()
        myDB.collection("Orders")
            .add(
                mapOf(
                    "customerName" to sPreference.getStringValue(SPreference.userName),
                    "pizzaName" to pizzaName,
                    "pizzaSize" to pizzaSize,
                    "pizzaQuantity" to pizzaQuantity,
                    "totalPrice" to totalPrice,
                    "dateTime" to LocalDateTime.now().toString(),
                    "status" to Constants.inProgress
                )
            )
            .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                Log.e(
                    "CustomerOrder", "DocumentSnapshot added with ID: " + documentReference.id
                )
            })
            .addOnFailureListener(OnFailureListener { e ->
                Log.e(
                    "CustomerOrder",
                    "Error adding document",
                    e
                )
            })

        val i = Intent(applicationContext, CustomerScreenActivity::class.java)
        Toast.makeText(
            applicationContext,
            "Order placed successfully",
            Toast.LENGTH_SHORT
        ).show()
        startActivity(i)
    }
}
