package com.piu.urbanrider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.adapters.TransportResultAdapter
import com.piu.urbanrider.models.TransportResult
import com.piu.urbanrider.models.TransportResults

class TransportResultsActivity : AppCompatActivity() {

    var transportResultAdapter : TransportResultAdapter? = null
    private lateinit var transportResultRecyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transport_results)

        transportResultRecyclerView = findViewById(R.id.recycler_view_transport_results)

        transportResultAdapter = TransportResultAdapter(this@TransportResultsActivity, TransportResults().getTestTransportResults("Car"))
        transportResultRecyclerView.adapter = transportResultAdapter

        val linearLayoutManager = LinearLayoutManager(this)
        transportResultRecyclerView.layoutManager = linearLayoutManager


    }
}