package com.piu.urbanrider.vehicles.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.GoogleMap
import com.piu.urbanrider.R
import com.piu.urbanrider.adapters.DrawerOptionAdapter
import com.piu.urbanrider.models.DrawerOptions
import com.piu.urbanrider.models.vehicles.RollerSkates

class RollerActivity : AppCompatActivity() {
    private var options: HashMap<String, String> = HashMap<String, String>()
    private lateinit var spinner: Spinner
    private lateinit var mMap: GoogleMap
    private lateinit var navigationDrawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerOptionAdapter: DrawerOptionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roller)


        // this.navigationDrawer = findViewById(R.id.drawer_layout)
        this.setupToolbar()
        //this.setupToggle()
        //this.setupDrawerOptions()

        populateDropBox()
        setOptions()
        buttonSetup()
        initializeOptions()
    }

    private fun initializeOptions(){
        options["protection"] = "Yes"
        options["type"] = "Linear"
        options["number"] = "3"
        options["shoes_number"] = "36"

    }

    private fun populateDropBox() {
        spinner = findViewById<Spinner>(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.shoes_number,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun setOptions() {
        val radioGroup_wheels_type = findViewById<RadioGroup>(R.id.wheels_type_rg)
        radioGroup_wheels_type.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                options["type"] = radio.text.toString()
                val layout = findViewById<LinearLayout>(R.id.nr_wheels_layout)
                if (radio.text.toString() == "Linear") {
                    layout.visibility = View.VISIBLE
                    val radioGroup_wheels_number = findViewById<RadioGroup>(R.id.nr_wheels_rg)
                    radioGroup_wheels_number.setOnCheckedChangeListener(
                        RadioGroup.OnCheckedChangeListener { group, checkedId ->
                            val radio_: RadioButton = findViewById(checkedId)
                            options["number"] = radio_.text.toString()
                        }
                    )
                } else {
                    layout.visibility = View.GONE
                    if (options.containsKey("number")) {
                        options.remove("number")
                    }
                }
            }
        )
        val radioGroup_protection_equipment = findViewById<RadioGroup>(R.id.protection_equip_rg)
        radioGroup_protection_equipment.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                options["protection"] = radio.text.toString()
            }
        )
    }

    private fun buttonSetup() {
        val button = findViewById<Button>(R.id.apply_button)
        button.setOnClickListener() {
            options["shoes_number"] = spinner.selectedItem.toString()
            val result = RollerSkates.instance.getRollerSkaters(options)
            System.out.println(result)
        }
    }

    private fun setupDrawerOptions() {
        val drawerOptionsRecyclerRef = findViewById<RecyclerView>(R.id.drawer_options_rv)
        this.drawerOptionAdapter =
            DrawerOptionAdapter(this@RollerActivity, DrawerOptions().getDrawerOptions())
        drawerOptionsRecyclerRef.adapter = this.drawerOptionAdapter
        drawerOptionsRecyclerRef.layoutManager = LinearLayoutManager(this)
    }

    private fun setupToolbar() {
        this.toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
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