package com.pizzaapp.yummypizzas.Utility

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SPreference(context: Context) {

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(Companion.PREFS_FILENAME, Context.MODE_PRIVATE)

    fun setStringValue(keyName: String, value: String) {
        sharedPrefs.edit { putString(keyName, value) }
    }

    fun getStringValue(keyName: String): String {
        return sharedPrefs.getString(keyName, "") ?: ""
    }

    fun setBooleanValue(keyName: String, value: Boolean) {
        sharedPrefs.edit { putBoolean(keyName, value) }
    }

    fun getBooleanValue(keyName: String): Boolean {
        return sharedPrefs.getBoolean(keyName, false)
    }

    fun setIntValue(keyName: String, value: Int) {
        sharedPrefs.edit { putInt(keyName, value) }
    }

    fun getIntValue(keyName: String): Int {
        return sharedPrefs.getInt(keyName, 0)
    }

    fun clearPreference() {
        val editor = sharedPrefs.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        const val PREFS_FILENAME = "shared_prefs_pizza_app"
        const val isLogin = "isLogin"
        const val isPersonalInfoFilled = "isPersonalInfoFilled"
        const val userName = "userName"
        const val userType = "userType"
    }
}