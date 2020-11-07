package com.example.pizzafinder.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzafinder.R
import com.example.pizzafinder.activity.MapsActivity
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
        holder.bindData(itemCity)
    }

    class CityHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var cityModel: CityModel? = null
        var binding: RowCityBinding

        init {
            v.setOnClickListener(this)
            binding = RowCityBinding.bind(v)
        }

        override fun onClick(v: View) {
            val intent = Intent(itemView.context, MapsActivity::class.java)
            intent.putExtra("cityId", cityModel?.cityId)
            intent.putExtra("cityName", cityModel?.cityName)
            itemView.context.startActivity(intent)
            Log.d("RecyclerView", "CLICK!")
        }

        fun bindData(itemCity: CityModel) {
            binding.tvCityName.text = itemCity.cityName
            cityModel = itemCity
        }
    }


}


