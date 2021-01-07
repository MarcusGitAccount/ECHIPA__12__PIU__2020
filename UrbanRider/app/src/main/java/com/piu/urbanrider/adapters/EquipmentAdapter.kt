package com.piu.urbanrider.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.R
import com.piu.urbanrider.models.TransportResult
import com.piu.urbanrider.models.vehicles.Bike
import com.piu.urbanrider.models.vehicles.Car
import com.piu.urbanrider.models.vehicles.RollerSkater
import com.piu.urbanrider.models.vehicles.Scooter
import com.piu.urbanrider.vehicles.activities.BikeManageActivity

class EquipmentAdapter(
    private val context: Context,
    private val equipmentResults: ArrayList<TransportResult>
) : RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder>() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder {
        val view = inflater.inflate(R.layout.private_equipment, parent, false)
        return EquipmentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return equipmentResults.size
    }

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {
        holder.bindData(equipmentResults[position])
    }

    inner class EquipmentViewHolder(private var view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        private lateinit var equipmentTitle: TextView
        private lateinit var equipmentDescription: TextView
        private lateinit var equipmentImage: ImageView
        private lateinit var equipmentManage: TextView
        private var data: TransportResult? = null


        init {
            equipmentTitle = view.findViewById(R.id.equipment_title)
            equipmentDescription = view.findViewById(R.id.equipment_description)
            equipmentImage = view.findViewById(R.id.equipment_image)
            equipmentManage = view.findViewById(R.id.equipment_manage)
            equipmentManage.setOnClickListener {
                when(data!!.type)
                {
                    "Bike"->{
                        val bikeAddActivity = Intent(it.context, BikeManageActivity::class.java)
                        bikeAddActivity.putExtra("ActionType","Manage")
                        bikeAddActivity.putExtra("Id", data?.id)
                        it.context.startActivity(bikeAddActivity)
                    }
                }

            }
        }

        fun bindData(data: TransportResult) {
            this.data = data

            equipmentTitle.text = data.title
            equipmentDescription.text = data.description
            equipmentImage.setImageResource(data.image)
        }

        override fun onClick(v: View?) {
            
        }
    }


}
