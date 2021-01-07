package com.piu.urbanrider.vehicles.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.R
import com.piu.urbanrider.adapters.DrawerOptionAdapter
import com.piu.urbanrider.models.DrawerOptions
import com.piu.urbanrider.models.vehicles.Bike
import com.piu.urbanrider.models.vehicles.Bikes
import com.piu.urbanrider.models.vehicles.RollerSkater
import com.piu.urbanrider.models.vehicles.RollerSkates

class RollerManageActivity : AppCompatActivity() {

    private lateinit var spinnerType: Spinner
    private lateinit var spinnerCurrency: Spinner
    private lateinit var spinnerProtection: Spinner
    private lateinit var spinnerShoes: Spinner
    private lateinit var spinnerWheels: Spinner
    private lateinit var rollerBrand: EditText
    private lateinit var rentPrice: EditText
    private lateinit var addButton: Button
    private lateinit var deleteButton: Button
    private lateinit var updateButton: Button
    private lateinit var image: ImageView
    private val REQUEST_CODE = 100
    private lateinit var actionType: String
    private var defaultImage: Int = R.drawable.ic_baseline_directions_bike_48

    private lateinit var navigationDrawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerOptionAdapter: DrawerOptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roller_manage)

        this.navigationDrawer = findViewById(R.id.drawer_layout)
        this.setupToolbar()
        this.setupToggle()
        this.setupDrawerOptions()

        populateDropBox()
        initComponents()
        displayLayoutButtons()

        spinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent != null) {
                    val wheelTxtVw = findViewById<TextView>(R.id.number_wheels_text_view)
                    if (parent.getItemAtPosition(position).toString() == "Linear") {
                        spinnerWheels.visibility = View.VISIBLE
                        wheelTxtVw.visibility = View.VISIBLE
                    } else {
                        spinnerWheels.visibility = View.GONE
                        wheelTxtVw.visibility = View.GONE
                    }
                }
            }
        }

    }

    private fun initComponents() {
        addButton = findViewById(R.id.add_rs)
        deleteButton = findViewById(R.id.delete_rs)
        updateButton = findViewById(R.id.update_rs)
        image = findViewById(R.id.new_image)
        rollerBrand = findViewById(R.id.roller_brand)
        rentPrice = findViewById(R.id.roller_price)
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
                    addNewRoller()
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
                        deleteRoller(id)
                    }
                }
                updateButton.setOnClickListener {
                    if (id != null) {
                        updateRoller(id)
                    }
                }
            }
        }
    }

    private fun setDefaults(id: Int?) {
        val data = id?.let { RollerSkates.instance.getRollerSkaterById(it) }
        rollerBrand.setText(data!!.productName)
        rentPrice.setText(data.price.toString())
        defaultImage = data.image
        val title = findViewById<TextView>(R.id.roller_layout_title)
        title.setText("Manage equipment")
    }

    private fun populateDropBox() {
        spinnerType = findViewById(R.id.roller_type_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.roller_skaters_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerType.adapter = adapter
        }

        spinnerCurrency = findViewById(R.id.roller_currency_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.currency,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCurrency.adapter = adapter
        }

        spinnerShoes = findViewById(R.id.shoes_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.shoes_number,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerShoes.adapter = adapter
        }

        spinnerProtection = findViewById(R.id.protection_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.protection,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerProtection.adapter = adapter
        }

        spinnerWheels = findViewById(R.id.wheels_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.wheels_number,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerWheels.adapter = adapter
        }

    }

    private fun addNewRoller() {

        val id = RollerSkates.instance.nextId()
        val newRollerSk = RollerSkater(
            id,
            rollerBrand.text.toString(),
            "admin",
            rentPrice.text.toString().toDouble(),
            spinnerCurrency.selectedItem.toString(),
            R.drawable.ic_baseline_rollerskates_48,
            spinnerShoes.selectedItem.toString().toInt(),
            spinnerType.selectedItem.toString(),
            spinnerProtection.selectedItem.toString(),
            if (spinnerWheels.isSelected) spinnerWheels.selectedItem.toString().toInt() else null
        )
        RollerSkates.instance.addRollerSkater(newRollerSk)
        finish()
    }

    private fun updateRoller(id: Int) {

        RollerSkates.instance.updateRollerSkater(
            RollerSkater(
                id,
                rollerBrand.text.toString(),
                "admin",
                rentPrice.text.toString().toDouble(),
                spinnerCurrency.selectedItem.toString(),
                R.drawable.ic_baseline_rollerskates_48,
                spinnerShoes.selectedItem.toString().toInt(),
                spinnerType.selectedItem.toString(),
                spinnerProtection.selectedItem.toString(),
                if (spinnerWheels.isSelected) spinnerWheels.selectedItem.toString()
                    .toInt() else null
            )
        )
        finish()
    }

    private fun deleteRoller(id: Int) {
        RollerSkates.instance.deleteRollerSkater(:wqid)
        finish()
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

    private fun setupDrawerOptions() {
        val drawerOptionsRecyclerRef = findViewById<RecyclerView>(R.id.drawer_options_rv)
        this.drawerOptionAdapter =
            DrawerOptionAdapter(this@RollerManageActivity, DrawerOptions().getDrawerOptions())
        drawerOptionsRecyclerRef.adapter = this.drawerOptionAdapter
        drawerOptionsRecyclerRef.layoutManager = LinearLayoutManager(this)
    }

    private fun setupToolbar() {
        this.toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_drawer_toggle)
    }

    private fun setupToggle() {
        this.toggle = ActionBarDrawerToggle(
            this,
            this.navigationDrawer,
            this.toolbar,
            R.string.open,
            R.string.close
        )
    }
}