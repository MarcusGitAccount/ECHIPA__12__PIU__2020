package com.piu.urbanrider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.adapters.DrawerOptionAdapter
import com.piu.urbanrider.models.DrawerOptions
import java.time.Duration

class ReviewActivity : AppCompatActivity() {

    private lateinit var navigationDrawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerOptionAdapter: DrawerOptionAdapter
    private lateinit var cancelButton: Button
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        this.navigationDrawer = findViewById(R.id.drawer_layout_review)
        this.setupToolbar()
        this.setupToggle()
        this.setupDrawerOptions()
        this.setupButtons()
    }

    private fun setupDrawerOptions() {
        val drawerOptionsRecyclerRef = findViewById<RecyclerView>(R.id.drawer_options_rv)
        this.drawerOptionAdapter = DrawerOptionAdapter(this@ReviewActivity, DrawerOptions().getDrawerOptions())
        drawerOptionsRecyclerRef.adapter = this.drawerOptionAdapter
        drawerOptionsRecyclerRef.layoutManager = LinearLayoutManager(this)
    }

    private fun setupToolbar() {
        this.toolbar = findViewById(R.id.toolbar)
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

    private fun setupButtons() {
        this.cancelButton = findViewById(R.id.review_cancel_button)
        this.submitButton = findViewById(R.id.review_submit_button)
        this.cancelButton.setOnClickListener {
            val intent = Intent(this@ReviewActivity, UserMapsActivity::class.java)
            startActivity(intent)
        }
        this.submitButton.setOnClickListener {
            val intent = Intent(this@ReviewActivity, UserMapsActivity::class.java)
            Toast.makeText(this@ReviewActivity, "Review submitted", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
    }
}