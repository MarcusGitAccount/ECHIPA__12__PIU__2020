package com.piu.urbanrider.vehicles.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.piu.urbanrider.R

class BusRideActivityWithNotification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_ride_with_notification)

        Handler().postDelayed({
            val builder = AlertDialog.Builder(this@BusRideActivityWithNotification, R.style.ModalTheme)
            val builderView = LayoutInflater.from(this@BusRideActivityWithNotification)
                .inflate(R.layout.modal_finish_bus_ride, null)

            builder.setView(builderView)
            val dialog = builder.create()
            dialog.show()
        }, 7000)
    }
}