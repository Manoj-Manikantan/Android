package com.pizzaapp.yummypizzas.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.pizzaapp.yummypizzas.Adapter.OrderListAdapter
import com.pizzaapp.yummypizzas.Entity.Order
import com.pizzaapp.yummypizzas.R
import com.pizzaapp.yummypizzas.Utility.Constants
import com.pizzaapp.yummypizzas.Utility.SPreference
import kotlinx.android.synthetic.main.activity_employee_order_tracker.*
import java.time.LocalDateTime


class EmployeeOrderTrackerActivity : AppCompatActivity(), OrderListAdapter.OrderClickListener {
    private lateinit var sPreference: SPreference

    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private lateinit var orderListAdapter: OrderListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_order_tracker)
        initUI()
    }

    private fun initUI() {
        sPreference = SPreference(this)
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()
        val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference!!.child(mUser!!.uid)
        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tvEmployeeName.text = "Hello " + snapshot.child("fullName").value
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        val allOrders: ArrayList<Order> = ArrayList()
        val myDb = FirebaseFirestore.getInstance()
        myDb.collection("Orders").get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.e("OrderTracking", document.id + " => " + document.data)
                        allOrders.add(
                            Order(
                                document.id,
                                document.data["customerName"].toString(),
                                document.data["pizzaName"].toString(),
                                document.data["pizzaSize"].toString(),
                                document.data["pizzaQuantity"].toString().toInt(),
                                document.data["totalPrice"].toString().toInt(),
                                document.data["dateTime"].toString(),
                                document.data["status"].toString()
                            )
                        )
                    }
                    if (allOrders.isNotEmpty()) {
                        orderListAdapter = OrderListAdapter(allOrders, this, this)
                        val linearLayout =
                            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                        rvOrderList.layoutManager = linearLayout
                        rvOrderList.adapter = orderListAdapter
                    }
                } else {
                    Log.e("OrderTracking", "Error getting documents.", task.exception)
                }
            }
    }

    fun onButtonPressed(view: View) {
        if (view.id == R.id.btnLogout) {
            sPreference.setBooleanValue(SPreference.isLogin, false)
            mAuth!!.signOut()
            Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainScreenActivity::class.java))
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStateChanged(item: Order, position: Int) {
        if (item.orderStatus == Constants.inProgress) {
            item.orderStatus = Constants.completed
        } else {
            item.orderStatus = Constants.inProgress
        }
        val myDB = FirebaseFirestore.getInstance()
        myDB.collection("Orders").document(item.orderId)
            .set(
                mapOf(
                    "customerName" to item.customerName,
                    "pizzaName" to item.pizzaName,
                    "pizzaSize" to item.pizzaSize,
                    "pizzaQuantity" to item.quantity,
                    "totalPrice" to item.price,
                    "dateTime" to LocalDateTime.now().toString(),
                    "status" to item.orderStatus
                )
            ).addOnSuccessListener {
                Log.e("CustomerOrder", "DocumentSnapshot updated ID: " + item.orderId)
            }
            .addOnFailureListener { e -> Log.e("CustomerOrder", "Error adding document", e) }
        orderListAdapter.notifyItemChanged(position)
    }
}