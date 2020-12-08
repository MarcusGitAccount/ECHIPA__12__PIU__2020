package com.piu.urbanrider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.adapters.NotificationAdapter
import com.piu.urbanrider.adapters.TicketAdapter
import com.piu.urbanrider.models.Notifications
import com.piu.urbanrider.models.Tickets

class TicketsActivity : AppCompatActivity() {
    var ticketAdapter:TicketAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)
        val recyclerRef = findViewById<RecyclerView>(R.id.notification_rv)
        ticketAdapter = TicketAdapter(this@TicketsActivity, Tickets().getTickets())
        recyclerRef.adapter = ticketAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerRef.layoutManager = linearLayoutManager
    }
}