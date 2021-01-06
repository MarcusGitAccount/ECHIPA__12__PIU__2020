package com.piu.urbanrider.vehicles.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.piu.urbanrider.R
import com.piu.urbanrider.adapters.DrawerOptionAdapter
import com.piu.urbanrider.models.DrawerOptions
import com.piu.urbanrider.models.vehicles.Bikes
import com.piu.urbanrider.models.vehicles.RollerSkates

class BikeActivity : AppCompatActivity(), OnMapReadyCallback {
    private var option: Int = 0
    private lateinit var mMap: GoogleMap
    private lateinit var navigationDrawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerOptionAdapter: DrawerOptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        this.navigationDrawer = findViewById(R.id.drawer_layout)
        this.setupToolbar()
        this.setupToggle()
        this.setupDrawerOptions()

        val radioGroup_protection_equipment = findViewById<RadioGroup>(R.id.bike_rg)
        radioGroup_protection_equipment.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
            }
        )

        val button = findViewById<Button>(R.id.apply_button)
        button.setOnClickListener() {
            val result = Bikes.instance.getBikes(option)
            val transportResults = Bikes.instance.transform(result)
            TODO("Connection to Transport Activity")
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

    private fun setupDrawerOptions() {
        val drawerOptionsRecyclerRef = findViewById<RecyclerView>(R.id.drawer_options_rv)
        this.drawerOptionAdapter = DrawerOptionAdapter(this@BikeActivity, DrawerOptions().getDrawerOptions())
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
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}