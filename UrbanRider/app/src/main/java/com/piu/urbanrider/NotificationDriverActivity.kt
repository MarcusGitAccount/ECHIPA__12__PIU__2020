package com.piu.urbanrider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.adapters.NotificationAdapter
import com.piu.urbanrider.models.Notifications

class NotificationDriverActivity : AppCompatActivity() {
    var notificationAdapter:NotificationAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notifications_list)
        val recyclerRef = findViewById<RecyclerView>(R.id.notification_rv)
        notificationAdapter = NotificationAdapter(this@NotificationDriverActivity, Notifications().getNotifications())
        recyclerRef.adapter = notificationAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerRef.layoutManager = linearLayoutManager

    }
}