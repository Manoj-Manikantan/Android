package com.pizzaapp.yummypizzas.Activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.core.OrderBy
import com.pizzaapp.yummypizzas.R
import kotlinx.android.synthetic.main.activity_customer_order_summary.*

class CustomerOrderSummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_order_summary)
        initUI()
    }

    private fun initUI() {
        val myDb = FirebaseFirestore.getInstance()
        val collection = myDb.collection("Orders").orderBy("dateTime")
        collection.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result!!
                    if (document.documents.size>0){
                        val lastIndex = document.documents.size-1
                        //val lastIndex = 0
                        Log.e(
                            "OrderTracking",
                            document.documents[lastIndex].id + " => " + document.documents[lastIndex].data
                        )
                        val latestOrder = document.documents[lastIndex].data
                        textViewOrderDate.text = "Order Date : " + latestOrder!!["dateTime"].toString()
                        textViewCustomerName.text = "Customer Name : " + latestOrder!!["customerName"].toString()
                        textViewPizzaName.text = "Pizza Name : " + latestOrder!!["pizzaName"].toString()
                        textViewPizzaSizeQty.text = "Size : " + latestOrder!!["pizzaSize"].toString() + " , Quantity : " + latestOrder!!["pizzaQuantity"].toString()
                        textViewTotalPrice.text = "Total Price : $" + latestOrder!!["totalPrice"].toString()
                    }
                } else {
                    Log.e("OrderTracking", "Error getting documents.", task.exception)
                }
            }
    }
}
