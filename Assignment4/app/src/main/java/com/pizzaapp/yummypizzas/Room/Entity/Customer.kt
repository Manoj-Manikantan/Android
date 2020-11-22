package com.pizzaapp.yummypizzas.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_table")
class Customer(
    @ColumnInfo(name = "userName") var userName: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "firstName") var firstName: String,
    @ColumnInfo(name = "lastName") var lastName: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "city") var city: String,
    @ColumnInfo(name = "postalCode") var postalCode: String
) {
    @PrimaryKey(autoGenerate = true)
    var customerId: Int = 0
}