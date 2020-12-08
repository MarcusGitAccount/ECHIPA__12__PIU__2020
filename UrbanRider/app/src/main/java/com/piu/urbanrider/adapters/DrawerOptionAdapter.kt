package com.piu.urbanrider.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.R
import com.piu.urbanrider.UserMapsActivity
import com.piu.urbanrider.models.DrawerOption

class DrawerOptionAdapter (private val context: Context, private val drawerOptions: ArrayList<DrawerOption>): RecyclerView.Adapter<DrawerOptionViewHolder>() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerOptionViewHolder {
        val view = inflater.inflate(R.layout.drawer_option, parent, false)
        return DrawerOptionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return drawerOptions.size
    }

    override fun onBindViewHolder(holder: DrawerOptionViewHolder, position: Int) {
        holder.bindData(drawerOptions[position])
    }
    fun addItem(index:Int, drawerOption: DrawerOption){
        drawerOptions.add(index, drawerOption)
    }
}

class DrawerOptionViewHolder(private var view: View): RecyclerView .ViewHolder(view), View.OnClickListener {
    private lateinit var drawerOptionText: TextView
    private lateinit var drawerOptionIcon: ImageView
    private var data: DrawerOption? = null

    init{
        drawerOptionText = view.findViewById(R.id.drawer_option_text)
        drawerOptionIcon = view.findViewById(R.id.drawer_option_icon)

        view.setOnClickListener(this)
    }
    fun bindData(data: DrawerOption){
        this.data = data
        drawerOptionText.text = data.title
        drawerOptionIcon.setImageResource(data.icon)

    }

    override fun onClick(p0: View?) {
        print("as")
    }
}