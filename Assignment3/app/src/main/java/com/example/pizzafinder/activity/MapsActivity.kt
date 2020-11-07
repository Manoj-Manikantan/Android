package com.example.pizzafinder.activity

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.pizzafinder.R
import com.example.pizzafinder.databinding.ActivityMapsBinding
import com.example.pizzafinder.model.PizzaRestuarantModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var selectCityId: Int = 0
    private lateinit var binding: ActivityMapsBinding
    private val markerList: ArrayList<PizzaRestuarantModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        initUI()
    }

    private fun initUI() {
        selectCityId = intent.getIntExtra("cityId", 0)
        val cityName = intent.getStringExtra("cityName")
        title = cityName
    }

    private fun fillMarkers(cityId: Int) {
        markerList.clear()
        mMap.clear()
        var selectedCity = LatLng(43.6532, 79.3832)

        when (cityId) {
            1 -> {
                /* Toronto */
               selectedCity = LatLng(43.6532, -79.3832)
                markerList.add(
                    PizzaRestuarantModel(
                        1,
                        "General Assembly Pizza",
                        43.668190,
                        -79.388340,
                        "47 Charles St W B, Toronto, ON M4Y 2R4",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        2,
                        "Jz’s Pizza",
                        43.645320,
                        -79.389790,
                        "232 Wellington St W, Toronto, ON M5V 3W1",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        3,
                        "Pizzeria Libretto University",
                        43.652390,
                        -79.387130,
                        "155 University Ave, Toronto, ON M5H 3B7",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        4,
                        "Pizza e Pazzi",
                        43.678082,
                        -79.443901,
                        "1182 St Clair Ave W, Toronto, ON M6E 1B4",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        5,
                        "North of Brooklyn Pizzeria",
                        43.646960,
                        -79.406440,
                        "650 Queen St W, Toronto, ON M6J 1E4",
                        "11AM to 11PM"
                    )
                )
            }
            2 -> {
                /* Kitchener */
                selectedCity = LatLng(43.4516, -80.4925)
                markerList.add(
                    PizzaRestuarantModel(
                        16,
                        "Papa John's Pizza",
                        43.441699,
                        -80.514644,
                        "517 Victoria St S, Kitchener, ON N2M 3A8",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        17,
                        "MJ's Gourmet Pizza",
                        43.446074,
                        -80.523549,
                        "11 Westwood Dr, Kitchener, ON N2M 2K5",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        18,
                        "Magic Pizza",
                        43.417619,
                        -80.535472,
                        "450 Westheights Dr, Kitchener, ON N2N 1M2",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        19,
                        "Pizza Roma",
                        43.405716,
                        -80.513101,
                        "192 Activa Ave, Kitchener, ON N2E 3T8",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        20,
                        "Pepi's Pizza",
                        43.454041,
                        -80.492739,
                        "87 Water St N, Kitchener, ON N2H 5A6",
                        "11AM to 11PM"
                    )
                )
            }
            3 -> {
                /* Waterloo */
                selectedCity = LatLng(43.4643, -80.5204)
                markerList.add(
                    PizzaRestuarantModel(
                        11,
                        "Famoso Neapolitan Pizzeria",
                        43.464722,
                        -80.522629,
                        "15 King St S, Waterloo, ON N2J 1N9",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        12,
                        "Gino's Pizza",
                        43.476681,
                        -80.525490,
                        "253 King St N Unit 2, Waterloo, ON N2J 2Y8",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        13,
                        "Pizza Pizza",
                        43.452760,
                        -80.552790,
                        "450 Erb St W Unit #4, Waterloo, ON N2T 1H4",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        14,
                        "Maestro's Pizza",
                        43.504320,
                        -80.509130,
                        "370 Eastbridge Blvd, Waterloo, ON N2K 3Z1",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        15,
                        "Davenport Pizza",
                        43.503570,
                        -80.529870,
                        "615 Davenport Rd, Waterloo, ON N2V 2G2",
                        "11AM to 11PM"
                    )
                )
            }
            4 -> {
                /* Saskatoon */
                selectedCity = LatLng(52.1579, -106.6702)
                markerList.add(
                    PizzaRestuarantModel(
                        6,
                        "Pizza Pirates",
                        52.143560,
                        -106.595006,
                        "205A 115 St E, Saskatoon, SK S7N 2E3",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        7,
                        "Famoso Neapolitan Pizzeria",
                        52.167382,
                        -106.640189,
                        "136 Primrose Dr # 300, Saskatoon, SK S7K 3W6",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        8,
                        "Red Swan Pizza",
                        52.115217,
                        -106.598270,
                        "3401 8 St E, Saskatoon, SK S7H 0W5",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        9,
                        "Pancho's Pizza & Pasta",
                        52.099358,
                        -106.591268,
                        "1945 McKercher Dr, Saskatoon, SK S7J 3V9",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        10,
                        "Kooko’s Pizza",
                        52.114291,
                        -106.646817,
                        "928 8 St E Unit C, Saskatoon, SK S7H 0R8",
                        "11AM to 11PM"
                    )
                )
            }
            5 -> {
                /* Hamilton */
                selectedCity = LatLng(43.2557, -79.8711)
                markerList.add(
                    PizzaRestuarantModel(
                        21,
                        "Chicago Style Pizza",
                        43.232093,
                        -79.847639,
                        "534 Upper Sherman Ave, Hamilton, ON L8V 3M1",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        22,
                        "Dough Box Wood Fired Pizza and Pasta",
                        43.257362,
                        -9.923970,
                        "1457 Main St W, Hamilton, ON L8S 1C9",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        23,
                        "Sasso",
                        43.203139,
                        -79.891259,
                        "1595 Upper James St, Hamilton, ON L9B 1K2",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        24,
                        "Buona Vita Pizza Inc.",
                        43.262155,
                        -79.877311,
                        "82 Queen St N #3, Hamilton, ON L8R 2V4",
                        "11AM to 11PM"
                    )
                )
                markerList.add(
                    PizzaRestuarantModel(
                        25,
                        "Mr Grande Pizza",
                        43.190466,
                        -79.841424,
                        "1157 Rymal Rd E, Hamilton, ON L8W 3M6",
                        "11AM to 11PM"
                    )
                )
            }
        }

        for (model: PizzaRestuarantModel in markerList) {
            val pizzaPointer = LatLng(model.lat, model.lon)
            mMap.addMarker(MarkerOptions().position(pizzaPointer).title(model.placeName))
        }

        /* move the camera to selected city */
        val zoomLevel:Float = 12.0f
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedCity, zoomLevel))
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        fillMarkers(selectCityId)
        mMap.setOnMarkerClickListener {
            Log.e("MapsActivity", "onMapReady: " + it.title)
            openPopup(it.title)
            return@setOnMarkerClickListener false
        }
    }

    private fun openPopup(title: String?) {
        for (model: PizzaRestuarantModel in markerList) {
            if (model.placeName == title){
                createPopup(model)
                break;
            }
        }

    }

    private fun createPopup(model: PizzaRestuarantModel) {
        val alertDialog: AlertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(model.placeName)
        alertDialog.setMessage("Address: " + model.address + "\n\nWorking hours: " + model.workingHours)
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, "OK"
        ) { dialog, _ -> dialog.dismiss() }
        alertDialog.show()
    }

    fun btnNormalClick(view: View) {
        if (view.id == R.id.btnNormal) {
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }

    fun btnSatelliteClick(view: View) {
        if (view.id == R.id.btnSatellite) {
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }
    }

}