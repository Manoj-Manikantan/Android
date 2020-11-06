package com.example.pizzafinder.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzafinder.R
import com.example.pizzafinder.databinding.RowCityBinding
import com.example.pizzafinder.model.CityModel

class SelectCityAdapter(private val cityList: ArrayList<CityModel>, private val context: Context) :
    RecyclerView.Adapter<SelectCityAdapter.CityHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.row_city, parent, false)
        return CityHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        val itemCity = cityList[position]
        holder.binding.tvCityName.text = itemCity.cityName
    }

    class CityHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var cityModel: CityModel? = null
        lateinit var binding: RowCityBinding

        init {
            v.setOnClickListener(this)
            binding = RowCityBinding.bind(v)
        }

        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }
    }


}


