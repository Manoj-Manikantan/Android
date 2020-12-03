package com.pizzaapp.yummypizzas.Room.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pizzaapp.yummypizzas.Room.Entity.Employee

@Dao
interface EmployeeDAO {

    @Query("SELECT * FROM employee_table")
    fun getAllEmployees(): List<Employee>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEmployee(employee: Employee)

    @Query("DELETE FROM employee_table")
    fun deleteAllEmployees()

}