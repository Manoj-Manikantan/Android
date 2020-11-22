package com.pizzaapp.yummypizzas.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_table")
class Order(
    @ColumnInfo(name = "customerId") var customerId: Int,
    @ColumnInfo(name = "productId") var productId: Int,
    @ColumnInfo(name = "employeeId") var employeeId: Int,
    @ColumnInfo(name = "orderDate") var orderDate: String,
    @ColumnInfo(name = "quantity") var orderQuantity: Int,
    @ColumnInfo(name = "status") var orderStatus: String
) {
    @PrimaryKey(autoGenerate = true)
    var orderId: Int = 0
}