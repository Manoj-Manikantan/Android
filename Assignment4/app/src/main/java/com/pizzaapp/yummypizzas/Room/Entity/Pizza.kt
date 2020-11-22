package com.pizzaapp.yummypizzas.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pizza_table")
class Pizza(
    @ColumnInfo(name = "pizzaType") var pizzaType: String,
    @ColumnInfo(name = "pizzaSize") var pizzaSize: String,
    @ColumnInfo(name = "pizzaToppings") var pizzaToppings: String
) {
    @PrimaryKey(autoGenerate = true)
    var productId: Int = 0
}