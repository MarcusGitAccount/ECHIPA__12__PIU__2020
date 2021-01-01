package com.piu.urbanrider.vehicles.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import com.piu.urbanrider.R
import com.piu.urbanrider.models.vehicles.RideShares
import com.piu.urbanrider.models.vehicles.RollerSkates

class CarActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private var options: HashMap<String, String> = HashMap<String, String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)

        populateDropBox()
        addButtonListener()
        initializeOptions()
    }
    private fun initializeOptions(){
        options["protection"] = "No"
        options["seats_number"] = "3"
        options["pets"] = "No"
    }

    private fun populateDropBox() {
        spinner = findViewById<Spinner>(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.number_of_seats,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }
    private fun addButtonListener() {
        val button = findViewById<Button>(R.id.apply_button)
        val checkBox_pets = findViewById<CheckBox>(R.id.checkbox_pets)
        val checkBox_protection = findViewById<CheckBox>(R.id.checkbox_protection)
        button.setOnClickListener() {
            options["seats_number"] = spinner.selectedItem.toString()
            options["pets"] = if (checkBox_pets.isChecked) "Yes" else "No"
            options["protection"] = if (checkBox_protection.isChecked) "Yes" else "No"
            val result = RideShares.instance.getRides(options)
            System.out.println(result)
        }
    }
}