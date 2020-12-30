package com.piu.urbanrider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.adapters.DrawerOptionAdapter
import com.piu.urbanrider.adapters.NotificationAdapter
import com.piu.urbanrider.adapters.TicketAdapter
import com.piu.urbanrider.models.DrawerOptions
import com.piu.urbanrider.models.Notifications
import com.piu.urbanrider.models.Tickets

class TicketsActivity : AppCompatActivity() {

    var ticketAdapter:TicketAdapter? = null
    private lateinit var navigationDrawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerOptionAdapter: DrawerOptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)
        val recyclerRef = findViewById<RecyclerView>(R.id.notification_rv)
        ticketAdapter = TicketAdapter(this@TicketsActivity, Tickets().getTickets())
        recyclerRef.adapter = ticketAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerRef.layoutManager = linearLayoutManager


        this.navigationDrawer = findViewById(R.id.drawer_layout_tickets)
        this.setupToolbar()
        this.setupToggle()
        this.setupDrawerOptions()
    }

    private fun setupDrawerOptions() {
        val drawerOptionsRecyclerRef = findViewById<RecyclerView>(R.id.drawer_options_rv)
        this.drawerOptionAdapter = DrawerOptionAdapter(this@TicketsActivity, DrawerOptions().getDrawerOptions())
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