package com.piu.urbanrider.vehicles.activities

import android.content.Intent
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
import com.piu.urbanrider.TransportResultsActivity
import com.piu.urbanrider.adapters.DrawerOptionAdapter
import com.piu.urbanrider.models.DrawerOptions
import com.piu.urbanrider.models.vehicles.RideShares
import com.piu.urbanrider.models.vehicles.RollerSkates

class CarActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var spinner: Spinner
    private var options: HashMap<String, String> = HashMap<String, String>()

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
        setContentView(R.layout.activity_car)

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
            val transportResults = RideShares.instance.transform(result)

            val intent = Intent(this@CarActivity, TransportResultsActivity::class.java)
            intent.putExtra("transportResults", transportResults)
            intent.putExtra("type", R.layout.layout_private_transport_result)
            intent.putExtra("transportImage", R.drawable.ic_baseline_directions_car_48)
            intent.putExtra("destinationString", this.destinationString)

            startActivity(intent)
        }
    }
    private fun setupDrawerOptions() {
        val drawerOptionsRecyclerRef = findViewById<RecyclerView>(R.id.drawer_options_rv)
        this.drawerOptionAdapter = DrawerOptionAdapter(this@CarActivity, DrawerOptions().getDrawerOptions())
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