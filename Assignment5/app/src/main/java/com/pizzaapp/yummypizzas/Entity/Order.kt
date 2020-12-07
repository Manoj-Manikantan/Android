package com.pizzaapp.yummypizzas.Entity

import androidx.room.ColumnInfo

class Order(
    @ColumnInfo(name = "orderId") var orderId: String,
    @ColumnInfo(name = "customerName") var customerName: String,
    @ColumnInfo(name = "pizzaName") var pizzaName: String,
    @ColumnInfo(name = "pizzaSize") var pizzaSize: String,
    @ColumnInfo(name = "quantity") var quantity: Int,
    @ColumnInfo(name = "price") var price: Int,
    @ColumnInfo(name = "orderDate") var orderDate: String,
    @ColumnInfo(name = "status") var orderStatus: String
)