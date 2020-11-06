package com.example.pizzafinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzafinder.databinding.ActivityMapsBinding
import com.example.pizzafinder.databinding.ActivitySelectCityBinding

class SelectCityActivity : AppCompatActivity() {

    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var binding: ActivitySelectCityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectCityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        linearLayout = LinearLayoutManager(this)
        binding.rvSelectCity.layoutManager = linearLayout
    }


}