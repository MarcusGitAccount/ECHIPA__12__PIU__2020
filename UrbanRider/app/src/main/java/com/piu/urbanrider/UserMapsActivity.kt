package com.piu.urbanrider

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.piu.urbanrider.adapters.DrawerOptionAdapter
import com.piu.urbanrider.models.*
import com.piu.urbanrider.vehicles.activities.*
import java.sql.DriverPropertyInfo

class UserMapsActivity : BasicDrawerActivity(), OnMapReadyCallback {

    companion object {
        const val INTENT_EXTRA_NOTIFICATION: String = "notification"
    }

    private lateinit var mMap: GoogleMap

    private lateinit var searchView: SearchView
    private lateinit var choiceBusImageView: ImageView
    private lateinit var choiceCarImageView: ImageView
    private lateinit var choiceBikeImageView: ImageView
    private lateinit var choiceRollerskatesImageView: ImageView
    private lateinit var choiceEscooterImageView: ImageView

    private var validInput : Boolean = false
    private lateinit var destinationLocation: LatLng
    private lateinit var sourceLocation: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        this.setup()
        this.setupTransportChoiceButtons()
        this.setupSearch()
        this.setupStartModal()
    }


    private fun setupTransportChoiceButtons() {
        this.choiceBusImageView = findViewById(R.id.choice_bus)
        this.choiceCarImageView = findViewById(R.id.choice_car)
        this.choiceBikeImageView = findViewById(R.id.choice_bike)
        this.choiceRollerskatesImageView = findViewById(R.id.choice_rollerskates)
        this.choiceEscooterImageView = findViewById(R.id.choice_escooter)

        this.choiceBusImageView.setOnClickListener({
            val intent = Intent(this@UserMapsActivity, TransportResultsActivity::class.java)
            checkAndStartActivity(intent, getString(R.string.string_non_empty_input_warning), TransportType.COMMON)
        })
        this.choiceCarImageView.setOnClickListener({
            val intent = Intent(this@UserMapsActivity, CarActivity::class.java)
            checkAndStartActivity(intent, getString(R.string.string_non_empty_input_warning), TransportType.PRIVATE)
        })
        this.choiceBikeImageView.setOnClickListener({
            val intent = Intent(this@UserMapsActivity, BikeActivity::class.java)
            checkAndStartActivity(intent, getString(R.string.string_non_empty_input_warning), TransportType.PRIVATE)
        })
        this.choiceRollerskatesImageView.setOnClickListener({
            val intent = Intent(this@UserMapsActivity, RollerActivity::class.java)
            checkAndStartActivity(intent, getString(R.string.string_non_empty_input_warning), TransportType.PRIVATE)
        })
        this.choiceEscooterImageView.setOnClickListener({
            val intent = Intent(this@UserMapsActivity, ScooterActivity::class.java)
            checkAndStartActivity(intent, getString(R.string.string_non_empty_input_warning), TransportType.PRIVATE)
        })
    }

    private fun setupSearch() {
        this.searchView = findViewById(R.id.destinationSearchView)
        this.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {return true}
            override fun onQueryTextChange(newText: String?): Boolean {
                if (searchView.query.toString().equals(""))
                    validInput = false
                else
                    validInput = true
                return true
            }
        })
    }

    private fun checkAndStartActivity(intent: Intent, message: String, transportType : TransportType) {
        if (this.validInput) {
            when(transportType) {
                TransportType.COMMON -> {
                    intent.putExtra("transportResults", TransportResults().getTestTransportResults("Bus"))
                    intent.putExtra("type", R.layout.layout_common_transport_result)
                    intent.putExtra("transportImage", R.drawable.ic_baseline_directions_bus_48)
                    intent.putExtra("destinationString", this.searchView.query)
                }
                TransportType.PRIVATE -> {
                    intent.putExtra("destinationString", this.searchView.query.toString())
                    intent.putExtra("destinationLocationLat", this.destinationLocation.latitude)
                    intent.putExtra("destinationLocationLong", this.destinationLocation.longitude)
                }
            }
            startActivity(intent)
        }
        else
            Toast.makeText(this@UserMapsActivity, message, Toast.LENGTH_LONG).show()
    }

    // Utils for maps
    private fun getBitmapDescriptor(id: Int): BitmapDescriptor? {
        val vectorDrawable: Drawable = resources.getDrawable(id)
        val h = 150
        val w = 150
        vectorDrawable.setBounds(0, 0, w, h)
        val bm: Bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bm)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val cluj = LatLng(46.770439, 23.591423)

        if (!DashboardOptions.isDriving) {
            mMap.addMarker(MarkerOptions().position(cluj).title("Marker in Cluj-Napoca"))
        } else {
            mMap.addMarker(MarkerOptions()
                .position(cluj)
                .title("Marker in Cluj-Napoca")
                .icon(getBitmapDescriptor(R.drawable.icons8_car_96)))
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cluj))
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    cluj.latitude,
                    cluj.longitude
                ), 16.0f
            )
        )
        this.destinationLocation = cluj
    }

    @SuppressLint("InflateParams")
    fun setupStartModal() {
        if (intent.extras != null) {
            // if null it will return
            val data = intent.getParcelableExtra<MainModalData>(INTENT_EXTRA_NOTIFICATION) ?: return
            val builder = AlertDialog.Builder(this, R.style.ModalTheme)
            val builderView = LayoutInflater.from(this).inflate(R.layout.main_modal_layout, null)

            builder.setView(builderView)

            val dialog = builder.create()

            builderView.findViewById<TextView>(R.id.dialog_title).text = data.title
            builderView.findViewById<TextView>(R.id.dialog_text2).text = data.content
            builderView.findViewById<Button>(R.id.main_modal_btn_dismiss).setOnClickListener() {
                dialog.dismiss()
            }

            dialog.show()
        }
    }
}