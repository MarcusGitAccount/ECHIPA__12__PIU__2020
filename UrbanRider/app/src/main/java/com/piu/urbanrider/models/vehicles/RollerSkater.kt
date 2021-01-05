package com.piu.urbanrider.models.vehicles

class RollerSkater(
    var id: Int,
    var productName: String,
    var owner: String,
    var price: Double,
    var currency: String,
    var image: Int,
    var shoesNumber: Int,
    var wheel_type: String,
    var protection: String,
    var nr_wheels: Int? = null
) {
}