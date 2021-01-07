package com.piu.urbanrider.vehicles.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.piu.urbanrider.adapters.DrawerOptionAdapter
import com.piu.urbanrider.models.DrawerOptions
import com.piu.urbanrider.models.vehicles.RollerSkates
import com.piu.urbanrider.models.vehicles.Scooters

class ScooterActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var spinnerHeight:Spinner
    private lateinit var spinnerBattery:Spinner
    private var options:HashMap<String, String> = HashMap()

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
        setContentView(R.layout.activity_scooter)

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
            val transportResults = Scooters.instance.transform(result)
            TODO("Connection to Transport Activity")
        }
    }
    private fun setupDrawerOptions() {
        val drawerOptionsRecyclerRef = findViewById<RecyclerView>(R.id.drawer_options_rv)
        this.drawerOptionAdapter = DrawerOptionAdapter(this@ScooterActivity, DrawerOptions().getDrawerOptions())
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