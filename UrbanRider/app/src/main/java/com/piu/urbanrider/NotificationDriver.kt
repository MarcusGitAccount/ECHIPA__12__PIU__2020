package com.piu.urbanrider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.adapters.NotificationAdapter
import com.piu.urbanrider.models.Notifications

class NotificationDriver : AppCompatActivity() {
    var notificationAdapter:NotificationAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notifications_list)
        val recyclerRef = findViewById<RecyclerView>(R.id.notification_rv)
        notificationAdapter = NotificationAdapter(this@NotificationDriver, Notifications().getNotifications())
        recyclerRef.adapter = notificationAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerRef.layoutManager = linearLayoutManager

    }
}