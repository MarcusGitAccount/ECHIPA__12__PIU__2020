package com.piu.urbanrider

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val errorContainer = findViewById<TextView>(R.id.login_error_view)

        findViewById<Button>(R.id.login_btn).setOnClickListener() {
            errorContainer.visibility = View.GONE

            val name = findViewById<TextView>(R.id.username).text.toString()
            val password = findViewById<TextView>(R.id.password).text.toString()

            if (!name.equals("admin@gmail.com") || !password.equals("123")) {
                errorContainer.text = "Invalid credentials"
                errorContainer.visibility = View.VISIBLE
            } else {
                val intent = Intent(this@MainActivity, UserMapsActivity::class.java)

                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        this.finishAffinity()
    }
}