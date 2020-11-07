package com.example.pizzafinder.model

class PizzaRestuarantModel {

    var placeID: Int = 0
    var placeName: String = ""
    var lat: Double = 0.0
    var lon: Double = 0.0
    var address: String = ""
    var workingHours: String = ""

    constructor(
        placeID: Int,
        placeName: String,
        lat: Double,
        lon: Double,
        address: String,
        workingHours: String
    ) {
        this.placeID = placeID
        this.placeName = placeName
        this.lat = lat
        this.lon = lon
        this.address = address
        this.workingHours = workingHours
    }
}