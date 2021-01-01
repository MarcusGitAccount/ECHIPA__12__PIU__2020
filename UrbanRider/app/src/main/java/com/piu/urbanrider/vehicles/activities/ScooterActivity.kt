package com.piu.urbanrider.vehicles.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.piu.urbanrider.R
import com.piu.urbanrider.models.vehicles.RollerSkates
import com.piu.urbanrider.models.vehicles.Scooters

class ScooterActivity : AppCompatActivity() {
    private lateinit var spinnerHeight:Spinner
    private lateinit var spinnerBattery:Spinner
    private var options:HashMap<String, String> = HashMap()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scooter)
        populateDropBox()
        radioGroupSetup()
        buttonSetup()
        initializeOptions()

    }
    private fun initializeOptions(){
        options["protection"] = "Yes"
        options["height"] = "150cm - 160cm"
        options["battery"] = "3"
    }

    private fun populateDropBox()
    {
        spinnerHeight = findViewById(R.id.spinner_height)
        ArrayAdapter.createFromResource(
            this,
            R.array.height_intervals,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerHeight.adapter = adapter}
        spinnerBattery = findViewById(R.id.spinner_battery)
        ArrayAdapter.createFromResource(
            this,
            R.array.battery_life,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerBattery.adapter = adapter}
    }
    private fun radioGroupSetup()
    {
        val radioGroup_protection_equipment = findViewById<RadioGroup>(R.id.protection_rg)
        radioGroup_protection_equipment.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                options["protection"] = radio.text.toString()
            }
        )
    }
    private fun buttonSetup()
    {
        val button = findViewById<Button>(R.id.apply_button)
        System.out.println("Button setup")
        button.setOnClickListener() {
            options["height"] = spinnerHeight.selectedItem.toString()
            options["battery"] = spinnerBattery.selectedItem.toString()
            val result = Scooters.instance.getScooters(options)
            System.out.println(result)
        }
    }
}