package com.piu.urbanrider

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.adapters.DrawerOptionAdapter
import com.piu.urbanrider.models.DrawerOptions

open class BasicDrawerActivity : AppCompatActivity() {

    protected lateinit var navigationDrawer: DrawerLayout

    protected lateinit var toolbar: Toolbar

    protected lateinit var toggle: ActionBarDrawerToggle

    protected lateinit var drawerOptionAdapter: DrawerOptionAdapter

    protected fun setup() {
        this.navigationDrawer = findViewById(R.id.drawer_layout)
        this.setupEvents()
        this.setupToolbar()
        this.setupToggle()
        this.setupDrawerOptions()
    }

    private fun setupEvents() {
        findViewById<Button>(R.id.driver_mode_btn).setOnClickListener {
            val intent = Intent(this, DriverDashboard::class.java)
            ContextCompat.startActivity(this, intent, null)
        }
    }

    private fun setupDrawerOptions() {
        val drawerOptionsRecyclerRef = findViewById<RecyclerView>(R.id.drawer_options_rv)
        this.drawerOptionAdapter =
            DrawerOptionAdapter(this, DrawerOptions().getDrawerOptions())
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