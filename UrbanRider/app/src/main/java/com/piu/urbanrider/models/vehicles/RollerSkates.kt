package com.piu.urbanrider.models.vehicles

import com.piu.urbanrider.R
import com.piu.urbanrider.models.TransportResult

class RollerSkates {
    private lateinit var rollerSkatesList: ArrayList<RollerSkater>

    private object INSTANCE {
        val INSTANCE = RollerSkates()
    }

    companion object {
        val instance: RollerSkates by lazy { INSTANCE.INSTANCE }
    }

    init {
        getRollerSkaters()
    }

    fun nextId(): Int {
        return rollerSkatesList.last().id + 1
    }

    fun getRollerSkaterById(id: Int): RollerSkater {
        return rollerSkatesList.first { it.id == id }
    }

    fun addRollerSkater(bike: RollerSkater) {
        rollerSkatesList.add(bike)
    }

    fun updateRollerSkater(new: RollerSkater) {
        val index = rollerSkatesList.indexOf(getRollerSkaterById(id = new.id))
        rollerSkatesList[index] = new
    }

    fun deleteRollerSkater(id: Int) {
        rollerSkatesList.remove(getRollerSkaterById(id = id))
    }

    private fun getRollerSkaters() {
        rollerSkatesList = ArrayList()
        rollerSkatesList.add(
            RollerSkater(
                1,
                "RollerSkaters1",
                "admin",
                2.5,
                "lei",
                R.drawable.ic_baseline_rollerskates_48,
                39,
                "Parallel",
                "Yes"
            )
        )
        rollerSkatesList.add(
            RollerSkater(
                2,
                "RollerSkaters2",
                "admin2",
                1.5,
                "lei",
                R.drawable.ic_baseline_rollerskates_48,
                38,
                "Parallel",
                "No"
            )
        )
        rollerSkatesList.add(
            RollerSkater(
                3,
                "RollerSkaters3",
                "admin",
                2.5,
                "lei",
                R.drawable.ic_baseline_rollerskates_48,
                40,
                "Linear",
                "Yes",
                3
            )
        )
        rollerSkatesList.add(
            RollerSkater(
                4,
                "RollerSkaters4",
                "admin2",
                1.75,
                "lei",
                R.drawable.ic_baseline_rollerskates_48,
                40,
                "Linear",
                "No",
                3
            )
        )
    }

    fun getRollerSkaters(options: HashMap<String, String>): List<RollerSkater> {
        var resultFilterType =
            (rollerSkatesList.filter { it.shoesNumber == options["shoes_number"]?.toInt() })
        resultFilterType = resultFilterType.filter { it.wheel_type == options["type"] }
        val resultFilterNumber =
            if (options.containsKey("number")) resultFilterType.filter { it.nr_wheels == options["number"]?.toInt() } else resultFilterType
        return resultFilterNumber.filter { it.protection == options["protection"] }
    }

    fun transform(result:List<RollerSkater>):ArrayList<TransportResult>{
        val list = ArrayList<TransportResult>()
        for (item in result)
        {
            list.add(TransportResult(item.id, "RollerSkater", item.productName, item.owner + " " + item.price + " " + item.currency, item.image ))
        }
        return list
    }

    fun getRollerSkatersByOwner(owner:String):ArrayList<TransportResult>
    {
        return transform(rollerSkatesList.filter { it.owner == owner })
    }
}