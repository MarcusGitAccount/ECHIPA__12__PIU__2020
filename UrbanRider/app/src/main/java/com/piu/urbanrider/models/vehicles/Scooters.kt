package com.piu.urbanrider.models.vehicles

import com.piu.urbanrider.R
import com.piu.urbanrider.models.TransportResult

class Scooters {

    private lateinit var scootersList: ArrayList<Scooter>

    private object INSTANCE {
        val INSTANCE = Scooters()
    }

    companion object {
        val instance: Scooters by lazy { INSTANCE.INSTANCE }
    }

    init {
        getScooters()
    }

    private fun getScooters() {
        scootersList = ArrayList()
        scootersList.add(
            Scooter(
                1,
                "Scooter1",
                "admin",
                1.3,
                "lei",
                R.drawable.ic_baseline_electric_scooter_48,
                "160cm - 170cm",
                4,
                "Yes"
            )
        )
        scootersList.add(
            Scooter(
                2,
                "Scooter2",
                "admin2",
                1.2,
                "lei",
                R.drawable.ic_baseline_electric_scooter_48,
                "160cm - 170cm",
                4,
                "No"
            )
        )
        scootersList.add(
            Scooter(
                3,
                "Scooter3",
                "admin2",
                1.7,
                "lei",
                R.drawable.ic_baseline_electric_scooter_48,
                "170cm - 180cm",
                7,
                "Yes"
            )
        )
        scootersList.add(
            Scooter(
                4,
                "Scooter4",
                "admin",
                2.0,
                "lei",
                R.drawable.ic_baseline_electric_scooter_48,
                "180cm +",
                9,
                "Yes"
            )
        )

    }
    fun getScooters(options:HashMap<String, String>):List<Scooter>{
        return scootersList.filter { it.acceptedHeight == options["height"] }
            .filter { it.batteryLife == options["battery"]?.toInt() }
            .filter{ it.protection == options["protection"]}
    }

    fun transform(result:List<Scooter>):ArrayList<TransportResult>{
        val list = ArrayList<TransportResult>()
        for (item in result)
        {
            list.add(TransportResult(item.id, "Bike", item.scooterBrand, item.owner + " " + item.price + " " + item.currency, item.image ))
        }
        return list
    }

    fun getScootersByOwner(owner:String):ArrayList<TransportResult>
    {
        return transform(scootersList.filter { it.owner == owner })

    }
}