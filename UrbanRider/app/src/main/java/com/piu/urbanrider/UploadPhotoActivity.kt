package com.piu.urbanrider

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class UploadPhotoActivity : AppCompatActivity() {
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_photo)
        this.button = findViewById(R.id.upload_ok)
    }

    fun uploadPhoto(view: View) {
        val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhoto, 1)
        button.isEnabled = true
    }

    fun takePhoto(view: View){
        val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePicture, 0)
        button.isEnabled = true
    }

    fun endFlow(view: View){
        val intent = Intent(this@UploadPhotoActivity, UserMapsActivity::class.java)
        startActivity(intent)
    }
}