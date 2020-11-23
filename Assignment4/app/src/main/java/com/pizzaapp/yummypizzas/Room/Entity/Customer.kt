package com.pizzaapp.yummypizzas.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_table")
class Customer(
    @ColumnInfo(name = "userName") var userName: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "fullName") var fullName: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "phoneNum") var phoneNum: String,
    @ColumnInfo(name = "postalCode") var postalCode: String,
    @ColumnInfo(name = "cardName") var cardName: String,
    @ColumnInfo(name = "cardType") var cardType: String,
    @ColumnInfo(name = "cardNumber") var cardNumber: String,
    @ColumnInfo(name = "expiryMonth") var expiryMonth: Int,
    @ColumnInfo(name = "expiryYear") var expiryYear: Int
) {
    @PrimaryKey(autoGenerate = true)
    var customerId: Int = 0
}