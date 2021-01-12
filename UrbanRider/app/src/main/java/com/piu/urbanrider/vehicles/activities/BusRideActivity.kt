package com.piu.urbanrider.vehicles.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.R
import com.piu.urbanrider.adapters.DrawerOptionAdapter
import com.piu.urbanrider.models.DrawerOptions

class BusRideActivity : AppCompatActivity() {
    private lateinit var destinationString: String

    private lateinit var navigationDrawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerOptionAdapter: DrawerOptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_ride)

        this.navigationDrawer = findViewById(R.id.drawer_layout)
        this.setupToolbar()
        this.setupToggle()
        this.setupDrawerOptions()

        this.destinationString = intent.getStringExtra("destinationString").toString()

        val sourceSearchView = findViewById<SearchView>(R.id.current_location_sv)
        val destinationSearchView = findViewById<SearchView>(R.id.destination_sv)

        sourceSearchView.setQuery("Current location", false)
        destinationSearchView.setQuery(this.destinationString, false)

        Handler().postDelayed({
            val notificationIntent = Intent(baseContext, BusRideActivityWithNotification::class.java)
            startActivity(notificationIntent)
        }, 10000)
    }

    private fun setupDrawerOptions() {
        val drawerOptionsRecyclerRef = findViewById<RecyclerView>(R.id.drawer_options_rv)
        this.drawerOptionAdapter = DrawerOptionAdapter(this@BusRideActivity, DrawerOptions().getDrawerOptions())
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