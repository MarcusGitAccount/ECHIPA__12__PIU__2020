package com.piu.urbanrider

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.piu.urbanrider.models.DashboardOptions

class DriverDashboard : BasicDrawerActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_dashboard)

        this.setup()

        findViewById<TextView>(R.id.dashboard_profit).text = DashboardOptions.profit.toString() + "RON"
        findViewById<TextView>(R.id.dashboard_ride_count).text = DashboardOptions.rideCount.toString()
        findViewById<TextView>(R.id.dashboard_review).text = DashboardOptions.review.toString()

        setActivateLogic()
        setStartLogic()

        findViewById<TextView>(R.id.dashboard_activate_btn).setOnClickListener {
            DashboardOptions.isActive = !DashboardOptions.isActive

            setActivateLogic()
        }

        findViewById<TextView>(R.id.dashboard_start_btn).setOnClickListener {
            DashboardOptions.isDriving = !DashboardOptions.isDriving

            setStartLogic()
        }
    }

    fun setActivateLogic() {
        if (DashboardOptions.isActive) {
            findViewById<TextView>(R.id.dashboard_activate_btn_label).text = "Go inactive"
            findViewById<TextView>(R.id.dashboard_activate_btn).text = "INACTIVATE"
        } else {
            findViewById<TextView>(R.id.dashboard_activate_btn_label).text = "Become active"
            findViewById<TextView>(R.id.dashboard_activate_btn).text = "ACTIVATE"
        }
    }

    fun setStartLogic() {
        if (DashboardOptions.isDriving) {
            findViewById<TextView>(R.id.dashboard_start_btn_label).text = "Stop driving"
            findViewById<TextView>(R.id.dashboard_start_btn).text = "STOP"

        } else {
            findViewById<TextView>(R.id.dashboard_start_btn_label).text = "Start driving"
            findViewById<TextView>(R.id.dashboard_start_btn).text = "START"

        }
    }
}