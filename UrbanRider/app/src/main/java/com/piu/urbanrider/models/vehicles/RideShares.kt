package com.piu.urbanrider.models.vehicles

import com.piu.urbanrider.R
import com.piu.urbanrider.models.TransportResult

class RideShares {
    private lateinit var ridesList: ArrayList<Car>

    private object INSTANCE {
        val INSTANCE = RideShares()
    }

    companion object {
        val instance: RideShares by lazy { INSTANCE.INSTANCE }
    }

    init {
        getRides()
    }

    private fun getRides() {
        ridesList = ArrayList()
        ridesList.add(
            Car(
                1,
                "Audi",
                "admin",
                2.5,
                "lei",
                R.drawable.ic_baseline_directions_car_48,
                4,
                "Yes",
                "Yes"
            )
        )
        ridesList.add(
            Car(
                2,
                "VW",
                "admin2",
                1.5,
                "lei",
                R.drawable.ic_baseline_directions_car_48,
                4,
                "No",
                "No"
            )
        )
        ridesList.add(
            Car(
                3,
                "Opel",
                "admin",
                3.5,
                "lei",
                R.drawable.ic_baseline_directions_car_48,
                5,
                "Yes",
                "Yes"
            )
        )
    }

    fun getRides(options: HashMap<String, String>): List<Car> {
        return (ridesList.filter { it.numberOfSeats == options["seats_number"]?.toInt() })
            .filter { it.petsFriendly == options["pets"] }
            .filter { it.protection == options["protection"] }
    }

    fun transform(result: List<Car>):ArrayList<TransportResult>{
        val list = ArrayList<TransportResult>()
        for (item in result)
        {
            list.add(TransportResult(item.id, "Car", item.carBrand, item.owner + " " + item.price + " " + item.currency, item.image ))
        }
        return list
    }

    fun getCarsByOwner(owner:String):ArrayList<TransportResult>
    {
        return transform(ridesList.filter { it.owner == owner })
    }
}