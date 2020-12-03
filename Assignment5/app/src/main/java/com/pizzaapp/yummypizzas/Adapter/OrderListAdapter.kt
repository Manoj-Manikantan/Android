package com.pizzaapp.yummypizzas.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pizzaapp.yummypizzas.R
import com.pizzaapp.yummypizzas.Room.Entity.Order
import com.pizzaapp.yummypizzas.Room.Entity.Pizza
import com.pizzaapp.yummypizzas.Utility.Constants
import com.pizzaapp.yummypizzas.databinding.RowOrderItemBinding

class OrderListAdapter(
    private val orderList: ArrayList<Order>,
    private val context: Context,
    private val listener: OrderClickListener
) :
    RecyclerView.Adapter<OrderListAdapter.OrderViewHolder>() {

    class OrderViewHolder(v: View, context: Context, listener: OrderClickListener) : RecyclerView.ViewHolder(v),
        View.OnClickListener {

        private var orderModel: Order? = null
        var binding: RowOrderItemBinding
        val myContext: Context
        val myListener: OrderClickListener

        init {
            v.setOnClickListener(this)
            binding = RowOrderItemBinding.bind(v)
            myContext = context
            myListener = listener
        }

        override fun onClick(v: View) {

        }

        @SuppressLint("ResourceAsColor", "NewApi")
        fun bindData(item: Order, position: Int) {
            binding.tvOrderId.text = "Order Id:" + item.orderId
            binding.tvCustomerName.text = "Customer Name:" + item.customerName
            binding.tvPizzaName.text = "Pizza: " + item.pizzaName
            binding.tvDate.text = "Date: " + item.orderDate
            binding.tvPizzaQuantity.text = "Quantity: " + item.quantity
            binding.tvPizzaSize.text = "Size: " + item.pizzaSize
            binding.tvPrice.text = "Price: " + item.price
            binding.tvStateLabel.text = item.orderStatus
            if (item.orderStatus == Constants.inProgress) {
                binding.btnState.setBackgroundColor(myContext.getColor(R.color.colorInProgress))
                binding.btnState.text = "Mark Delivered"
            } else {
                binding.btnState.setBackgroundColor(myContext.getColor(R.color.colorDelivered))
                binding.btnState.text = "Mark In Progress"
            }
            binding.btnState.setOnClickListener {
                myListener.onStateChanged(item, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflatedView =
            LayoutInflater.from(context).inflate(R.layout.row_order_item, parent, false)
        return OrderViewHolder(inflatedView, context, listener)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    interface OrderClickListener {
        fun onStateChanged(item: Order, position: Int)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = orderList[position]
        holder.bindData(item, position)
    }

}