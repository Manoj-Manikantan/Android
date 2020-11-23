package com.pizzaapp.yummypizzas.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_table")
class Order(
    @ColumnInfo(name = "customerName") var customerName: String,
    @ColumnInfo(name = "pizzaName") var pizzaName: String,
    @ColumnInfo(name = "pizzaSize") var pizzaSize: String,
    @ColumnInfo(name = "quantity") var quantity: Int,
    @ColumnInfo(name = "price") var price: Int,
    @ColumnInfo(name = "orderDate") var orderDate: String,
    @ColumnInfo(name = "status") var orderStatus: String
) {
    @PrimaryKey(autoGenerate = true)
    var orderId: Int = 0
}