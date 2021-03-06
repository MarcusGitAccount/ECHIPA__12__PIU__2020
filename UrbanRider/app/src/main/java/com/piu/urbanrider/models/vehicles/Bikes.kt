package com.piu.urbanrider.models.vehicles

import com.piu.urbanrider.R
import com.piu.urbanrider.models.TransportResult

class Bikes {

    private lateinit var bikesList: ArrayList<Bike>

    private object INSTANCE {
        val INSTANCE = Bikes()
    }

    companion object {
        val instance: Bikes by lazy { INSTANCE.INSTANCE }
    }

    init {
        getBikes()
    }

    private fun getBikes() {
        bikesList = ArrayList()
        bikesList.add(
            Bike(
                1,
                "Bike1",
                "admin",
                0.7,
                "lei",
                R.drawable.ic_baseline_directions_bike_48,
                0
            )
        )
        bikesList.add(
            Bike(
                2,
                "Bike2",
                "admin",
                0.8,
                "lei",
                R.drawable.ic_baseline_directions_bike_48,
                1
            )
        )
        bikesList.add(
            Bike(
                3,
                "Bike3",
                "admi2",
                0.65,
                "lei",
                R.drawable.ic_baseline_directions_bike_48,
                1
            )
        )

    }

    fun nextId(): Int {
        return bikesList.last().id + 1
    }

    fun getBikeById(id: Int): Bike {
        return bikesList.first { it.id == id }
    }

    fun addBike(bike: Bike) {
        bikesList.add(bike)
    }

    fun updateBike(newBike: Bike) {
        val index = bikesList.indexOf(getBikeById(id = newBike.id))
        bikesList[index] = newBike
    }

    fun deleteBike(id: Int) {
        bikesList.remove(getBikeById(id = id))
    }

    fun getBikes(option: Int): List<Bike> {
        return bikesList.filter { it.bikeType == option }
    }

    fun transform(result: List<Bike>): ArrayList<TransportResult> {
        val list = ArrayList<TransportResult>()
        for (item in result) {
            list.add(
                TransportResult(
                    item.id,
                    "Bike",
                    item.bikeBrand,
                    item.owner + " " + item.price + " " + item.currency,
                    item.image
                )
            )
        }
        return list
    }

    fun getBikesByOwner(owner: String): ArrayList<TransportResult> {
        return transform(bikesList.filter { it.owner == owner })
    }

}