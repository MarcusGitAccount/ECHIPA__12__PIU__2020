package com.piu.urbanrider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible

class TimerActivity : AppCompatActivity() {
    private lateinit var chronometer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        chronometer = findViewById(R.id.chronometer)
        chronometer.start()
    }

    fun finishRide(view: View){
        chronometer.stop()
        val builder = AlertDialog.Builder(this, R.style.ModalTheme)
        val builderView = LayoutInflater.from(this).inflate(R.layout.modal_finish_ride, null)
        builder.setView(builderView)
        builderView.findViewById<TextView>(R.id.time_info2).setText(((SystemClock.elapsedRealtime() - chronometer.getBase())/1000).toString())
        val dialog = builder.create()
        builderView.findViewById<Button>(R.id.modal_finish_ride).setOnClickListener() {
            val intent = Intent(this@TimerActivity, UploadPhotoActivity::class.java)
            startActivity(intent)
            dialog.dismiss()
        }
        dialog.show()
    }

}