package com.piu.urbanrider.vehicles.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import com.piu.urbanrider.R
import com.piu.urbanrider.models.vehicles.Bikes
import com.piu.urbanrider.models.vehicles.RollerSkates

class BikeActivity : AppCompatActivity() {
    private var option: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike)

        val radioGroup_protection_equipment = findViewById<RadioGroup>(R.id.bike_rg)
        radioGroup_protection_equipment.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
            }
        )

        val button = findViewById<Button>(R.id.apply_button)
        button.setOnClickListener() {
            val result = Bikes.instance.getBikes(option)
            System.out.println(result)
        }
    }
    fun radio_button_on_click(view: View)
    {
        val r0 = findViewById<RadioButton>(R.id.mountain_bike_option)
        val r1 = findViewById<RadioButton>(R.id.city_bike_option)
        val name = view.context.resources.getResourceEntryName(view.id)
        if (name == "city_bike_option"){
            option = 1
            r0.isChecked = false
            r1.isChecked = true
        }else{
            option = 0
            r0.isChecked = true
            r1.isChecked = false
        }
    }
}