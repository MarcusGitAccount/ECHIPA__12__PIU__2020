package com.piu.urbanrider.vehicles.activities

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import com.piu.urbanrider.R
import com.piu.urbanrider.models.vehicles.Bike
import com.piu.urbanrider.models.vehicles.Bikes
import kotlinx.android.synthetic.main.activity_car.*
import org.jetbrains.anko.find

class BikeManageActivity : AppCompatActivity() {
    private lateinit var spinnerType: Spinner
    private lateinit var spinnerCurrency: Spinner
    private lateinit var bikeBrand: EditText
    private lateinit var rentPrice: EditText
    private lateinit var addButton: Button
    private lateinit var deleteButton: Button
    private lateinit var updateButton: Button
    private lateinit var image: ImageView
    private val REQUEST_CODE = 100
    private lateinit var actionType: String
    private var defaultImage: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike_manage)
        populateDropBox()
        initComponents()
        displayLayoutButtons()

    }

    private fun initComponents() {
        addButton = findViewById(R.id.add_bike)
        deleteButton = findViewById(R.id.delete_bike)
        updateButton = findViewById(R.id.update_bike)
        image = findViewById(R.id.new_image)
        bikeBrand = findViewById(R.id.bike_brand)
        rentPrice = findViewById(R.id.bike_price)
        actionType = intent.getStringExtra("ActionType").toString()
    }

    private fun displayLayoutButtons() {
        when (actionType) {
            "Add" -> {
                deleteButton.visibility = View.GONE
                updateButton.visibility = View.GONE
                addButton.visibility = View.VISIBLE
                addButton.setOnClickListener()
                {
                    addNewBike()
                }
            }
            "Manage" -> {
                deleteButton.visibility = View.VISIBLE
                updateButton.visibility = View.VISIBLE
                addButton.visibility = View.GONE
                val id = intent?.getIntExtra("Id", 0)
                setDefaults(id)
                deleteButton.setOnClickListener {
                    if (id != null) {
                        deleteBike(id)
                    }
                }
                updateButton.setOnClickListener {
                    if (id != null) {
                        updateBike(id)
                    }
                }
            }
        }
    }

    private fun setDefaults(id: Int?) {
        val data = id?.let { Bikes.instance.getBikeById(it) }
        bikeBrand.setText(data!!.bikeBrand)
        rentPrice.setText(data.price.toString())
        defaultImage = data.image
        val title = findViewById<TextView>(R.id.bike_layout_title)
        title.setText("Manage equipment")
    }

    private fun populateDropBox() {
        spinnerType = findViewById(R.id.bike_type_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.bike_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerType.adapter = adapter
        }

        spinnerCurrency = findViewById(R.id.bike_currency_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.currency,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCurrency.adapter = adapter
        }
    }

    private fun addNewBike() {

        val id = Bikes.instance.nextId()
        //val newBike = Bike(id, bikeBrand.toString(), "admin", rentPrice.toString().toDouble(), spinnerCurrency.toString(), image.getImageResource() )
    }

    private fun updateBike(id: Int) {
        val type = if (spinnerType.toString() == "Mountain") 0 else 1
        Bikes.instance.updateBike(
            Bike(
                id,
                bikeBrand.text.toString(),
                "admin",
                rentPrice.text.toString().toDouble(),
                spinnerCurrency.toString(),
                defaultImage,
                type
            )
        )
        finish()
    }

    private fun deleteBike(id: Int) {
        Bikes.instance.deleteBike(id)
    }

    fun uploadPhoto(view: View) {
        val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhoto, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            image.setImageURI(data?.data) // handle chosen image
        }
    }
}