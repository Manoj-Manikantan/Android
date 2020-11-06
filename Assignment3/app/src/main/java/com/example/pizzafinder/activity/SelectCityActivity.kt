package com.example.pizzafinder.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzafinder.adapter.SelectCityAdapter
import com.example.pizzafinder.databinding.ActivityMapsBinding
import com.example.pizzafinder.databinding.ActivitySelectCityBinding
import com.example.pizzafinder.model.CityModel

class SelectCityActivity : AppCompatActivity() {

    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var binding: ActivitySelectCityBinding
    private lateinit var adapter: SelectCityAdapter
    private val cityList: ArrayList<CityModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectCityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        linearLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvSelectCity.layoutManager = linearLayout
        fillCityList()
        adapter = SelectCityAdapter(cityList, this)
        binding.rvSelectCity.adapter = adapter
    }

    fun fillCityList() {
        cityList.add(CityModel(1, "Toronto"))
        cityList.add(CityModel(2, "Kitchener"))
        cityList.add(CityModel(3, "Waterloo"))
        cityList.add(CityModel(4, "Saskatoon"))
        cityList.add(CityModel(5, "Hamilton"))
    }

}