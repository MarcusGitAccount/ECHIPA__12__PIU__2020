package com.piu.urbanrider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.adapters.DrawerOptionAdapter
import com.piu.urbanrider.adapters.EquipmentAdapter
import com.piu.urbanrider.adapters.TransportResultAdapter
import com.piu.urbanrider.models.DrawerOptions
import com.piu.urbanrider.models.TransportResult
import com.piu.urbanrider.models.TransportResults
import com.piu.urbanrider.models.vehicles.Bikes
import com.piu.urbanrider.models.vehicles.RideShares
import com.piu.urbanrider.models.vehicles.RollerSkates
import com.piu.urbanrider.models.vehicles.Scooters
import com.piu.urbanrider.vehicles.activities.BikeManageActivity
import java.util.ArrayList

class OwnedEquipmentsActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {

    private lateinit var navigationDrawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerOptionAdapter: DrawerOptionAdapter

    private lateinit var addButton: Button

    var equipmentAdapter: EquipmentAdapter? = null
    private lateinit var equipmentRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owned_equipments)

        this.navigationDrawer = findViewById(R.id.drawer_layout)
        this.setupToolbar()
        this.setupToggle()
        this.setupDrawerOptions()


        equipmentRecyclerView = findViewById(R.id.recycler_view_equipments)

        equipmentAdapter = EquipmentAdapter(this@OwnedEquipmentsActivity, getOwnedVehicles())
        equipmentRecyclerView.adapter = equipmentAdapter

        val linearLayoutManager = LinearLayoutManager(this)
        equipmentRecyclerView.layoutManager = linearLayoutManager

    }

    private fun getOwnedVehicles(): ArrayList<TransportResult> {
        val listEquipments = ArrayList<TransportResult>()
        listEquipments.addAll(Bikes.instance.getBikesByOwner("admin"))
        listEquipments.addAll(RideShares.instance.getCarsByOwner("admin"))
        listEquipments.addAll(RollerSkates.instance.getRollerSkatersByOwner("admin"))
        listEquipments.addAll(Scooters.instance.getScootersByOwner("admin"))
        return listEquipments
    }

    private fun setupDrawerOptions() {
        val drawerOptionsRecyclerRef = findViewById<RecyclerView>(R.id.drawer_options_rv)
        this.drawerOptionAdapter =
            DrawerOptionAdapter(this@OwnedEquipmentsActivity, DrawerOptions().getDrawerOptions())
        drawerOptionsRecyclerRef.adapter = this.drawerOptionAdapter
        drawerOptionsRecyclerRef.layoutManager = LinearLayoutManager(this)
    }

    private fun setupToolbar() {
        this.toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_drawer_toggle)
    }

    private fun setupToggle() {
        this.toggle = ActionBarDrawerToggle(
            this,
            this.navigationDrawer,
            this.toolbar,
            R.string.open,
            R.string.close
        )
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        println("Menu item clicked")
        return when (item.itemId) {
            R.id.add_bike -> {
                val bikeAddActivity = Intent(baseContext, BikeManageActivity::class.java)
                bikeAddActivity.putExtra("ActionType", "Add")
                startActivity(bikeAddActivity)
                //equipmentAdapter!!.notifyDataSetChanged()
                super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showPopUpMenu(view: View) {
        PopupMenu(this, view).apply {
            // MainActivity implements OnMenuItemClickListener
            setOnMenuItemClickListener(this@OwnedEquipmentsActivity)
            inflate(R.menu.add_vehicle_menu)
            show()
        }
    }
}