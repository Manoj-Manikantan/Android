package com.pizzaapp.yummypizzas.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pizza_table")
class Pizza(
    @ColumnInfo(name = "pizzaName") var pizzaName: String,
    @ColumnInfo(name = "price") var pizzaPrice: Float,
    @ColumnInfo(name = "category") var pizzaCategory: String
) {
    @PrimaryKey(autoGenerate = true)
    var productId: Int = 0
}