package com.piu.urbanrider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.ActionBarOverlayLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.piu.urbanrider.adapters.DrawerOptionAdapter
import com.piu.urbanrider.models.DrawerOptions

class UserMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var navigationDrawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerOptionAdapter: DrawerOptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        this.navigationDrawer = findViewById(R.id.drawer_layout)
        this.setupToolbar()
        this.setupToggle()
        this.setupDrawerOptions()
    }

    private fun setupDrawerOptions() {
        val drawerOptionsRecyclerRef = findViewById<RecyclerView>(R.id.drawer_options_rv)
        this.drawerOptionAdapter = DrawerOptionAdapter(this@UserMapsActivity, DrawerOptions().getDrawerOptions())
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