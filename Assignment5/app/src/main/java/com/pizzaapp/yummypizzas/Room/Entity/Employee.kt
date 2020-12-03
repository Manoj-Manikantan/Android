package com.pizzaapp.yummypizzas.Room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_table")
class Employee(
    @ColumnInfo(name = "userName") var userName: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "firstName") var firstName: String,
    @ColumnInfo(name = "lastName") var lastName: String
) {
    @PrimaryKey(autoGenerate = true)
    var employeeId: Int = 0
}