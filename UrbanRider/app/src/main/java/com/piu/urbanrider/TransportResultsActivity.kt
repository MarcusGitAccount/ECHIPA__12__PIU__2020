package com.piu.urbanrider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.adapters.DrawerOptionAdapter
import com.piu.urbanrider.adapters.TransportResultAdapter
import com.piu.urbanrider.models.DrawerOptions
import com.piu.urbanrider.models.TransportResult
import com.piu.urbanrider.models.TransportResults

class TransportResultsActivity : AppCompatActivity() {

    var transportResultAdapter : TransportResultAdapter? = null
    private lateinit var transportResultRecyclerView : RecyclerView
    private lateinit var navigationDrawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerOptionAdapter: DrawerOptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport_results)

        transportResultRecyclerView = findViewById(R.id.recycler_view_transport_results)

        transportResultAdapter = TransportResultAdapter(this@TransportResultsActivity, TransportResults().getTestTransportResults("Bus"))
        transportResultRecyclerView.adapter = transportResultAdapter

        val linearLayoutManager = LinearLayoutManager(this)
        transportResultRecyclerView.layoutManager = linearLayoutManager

        this.navigationDrawer = findViewById(R.id.drawer_layout_transport_results)
        this.setupToolbar()
        this.setupToggle()
        this.setupDrawerOptions()
    }

    private fun setupDrawerOptions() {
        val drawerOptionsRecyclerRef = findViewById<RecyclerView>(R.id.drawer_options_rv)
        this.drawerOptionAdapter = DrawerOptionAdapter(this@TransportResultsActivity, DrawerOptions().getDrawerOptions())
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