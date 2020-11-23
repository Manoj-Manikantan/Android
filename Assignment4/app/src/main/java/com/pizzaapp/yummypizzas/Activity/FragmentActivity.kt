package com.pizzaapp.yummypizzas.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.pizzaapp.yummypizzas.R


data class Order(val custName: String, val orderId: Int, val orderStatus: String, val button: String)

class FragmentActivity : Fragment() {

    private val orderDetails = listOf(
        Order("Manoj", 1, "In progess", "Button"),
        Order("Masum", 2, "Completed", "Button"),
        Order("John", 3, "Cancelled", "Button"),
        Order("Wick", 4, "In progess", "Button")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.activity_fragment, container, false)

    // populate the views now that the layout has been inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // RecyclerView node initialized here
        /*list_recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = ListAdapter(orderDetails)
        }*/
    }

    companion object {
        fun newInstance(): FragmentActivity = FragmentActivity()
    }


}