package com.piu.urbanrider.vehicles.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.piu.urbanrider.R
import com.piu.urbanrider.TransportResultsActivity
import com.piu.urbanrider.adapters.DrawerOptionAdapter
import com.piu.urbanrider.models.DrawerOptions
import com.piu.urbanrider.models.vehicles.RollerSkates

class RollerActivity : AppCompatActivity(), OnMapReadyCallback {
    private var options: HashMap<String, String> = HashMap<String, String>()
    private lateinit var spinner: Spinner

    private lateinit var mMap: GoogleMap
    private lateinit var navigationDrawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerOptionAdapter: DrawerOptionAdapter
    private lateinit var destinationSearchView: SearchView
    private lateinit var sourceSearchView: SearchView

    private lateinit var destinationString: String
    private lateinit var destinationLocation: LatLng
    private lateinit var sourceLocation: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roller)

        this.sourceSearchView = findViewById(R.id.current_location_sv)
        this.destinationSearchView = findViewById(R.id.destination_sv)

        this.destinationString = intent.getStringExtra("destinationString").toString()

        this.sourceSearchView.setQuery("Current location", false)
        this.destinationSearchView.setQuery(this.destinationString, false)

        this.navigationDrawer = findViewById(R.id.drawer_layout)

        this.setupToolbar()
        this.setupToggle()
        this.setupDrawerOptions()

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
            val transportResults = RollerSkates.instance.transform(result)

            val intent = Intent(this@RollerActivity, TransportResultsActivity::class.java)
            intent.putExtra("transportResults", transportResults)
            intent.putExtra("type", R.layout.layout_private_transport_result)
            intent.putExtra("transportImage", R.drawable.ic_baseline_rollerskates_48)
            intent.putExtra("destinationString", this.destinationString)
            startActivity(intent)
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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        this.destinationLocation = LatLng(intent.getDoubleExtra("destinationLocationLat", 0.0), intent.getDoubleExtra("destinationLocationLong", 0.0))
        mMap = googleMap

        mMap.addMarker(MarkerOptions().position(this.destinationLocation).title("Marker"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(this.destinationLocation))
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    this.destinationLocation.latitude,
                    this.destinationLocation.longitude
                ), 16.0f
            )
        )
    }

}