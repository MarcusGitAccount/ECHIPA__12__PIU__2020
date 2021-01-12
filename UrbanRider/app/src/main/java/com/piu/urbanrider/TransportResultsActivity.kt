package com.piu.urbanrider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
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
    private lateinit var destinationSearchView: SearchView
    private lateinit var sourceSearchView: SearchView
    private lateinit var transportImageView: ImageView
    private lateinit var infoTextView: TextView
    private var transportImage: Int = 0

    private lateinit var transportResults : ArrayList<TransportResult>
    private var transportResultsType : Int = 0
    lateinit var destinationString:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport_results)

        this.destinationSearchView = findViewById(R.id.destination_sv)
        this.sourceSearchView = findViewById(R.id.current_location_sv)
        this.transportImageView = findViewById(R.id.bike_icon)
        this.infoTextView = findViewById(R.id.info_text)
        this.destinationSearchView.setQuery(intent.getStringExtra("destinationString"), false)
        this.sourceSearchView.setQuery("Current location", false)
        this.transportImageView.setImageResource(intent.getIntExtra("transportImage", 0))

        this.navigationDrawer = findViewById(R.id.drawer_layout_transport_results)
        this.setupToolbar()
        this.setupToggle()
        this.setupDrawerOptions()

        this.destinationString = intent.getStringExtra("destinationString").toString()
        this.transportResults = intent.getSerializableExtra("transportResults") as ArrayList<TransportResult>
        this.transportResultsType = intent.getIntExtra("type", 0)

        transportResultRecyclerView = findViewById(R.id.recycler_view_transport_results)
        transportResultAdapter = TransportResultAdapter(this@TransportResultsActivity, transportResults, this.transportResultsType, destinationString)
        transportResultRecyclerView.adapter = transportResultAdapter

        val linearLayoutManager = LinearLayoutManager(this)
        transportResultRecyclerView.layoutManager = linearLayoutManager

        if (this.transportResults.size == 0) {
            this.infoTextView.text = "No items found matching your search options."
            this.infoTextView.isVisible = true
            this.transportResultRecyclerView.isVisible = false
        }
        else
            this.infoTextView.isVisible = false
        this.transportResultRecyclerView.isVisible = true
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