package com.piu.urbanrider.models

import com.piu.urbanrider.R

class TransportResults {
    private var transportResults = ArrayList<TransportResult>()

    fun getTestTransportResults(type : String): ArrayList<TransportResult> {
        val list = ArrayList<TransportResult>();
        when (type) {
            "Bus" -> {
                list.add(
                    TransportResult(0, type, "24B", "Arrives in 10m", R.drawable.ic_baseline_directions_bus_48)
                )
                list.add(
                    TransportResult(0, type, "4", "Arrives in 11m", R.drawable.ic_baseline_directions_bus_48)
                )
            }
            "Car" -> {
                list.add(
                    TransportResult(0, type, "Audi A4", "Ion Ionescu, 0.55$/km", R.drawable.ic_baseline_directions_car_48)
                )
                list.add(
                    TransportResult(0, type, "Skoda Octavia", "Marian Popescu, 0.5$/min", R.drawable.ic_baseline_directions_car_48)
                )
            }
        }
        this.transportResults = list
        return list
    }
}