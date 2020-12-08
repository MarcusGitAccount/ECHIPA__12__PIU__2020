package com.piu.urbanrider.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.R
import com.piu.urbanrider.models.Notification

class NotificationAdapter(private val context: Context, private val notifications: ArrayList<Notification>):RecyclerView.Adapter<NotificationViewHolder>(){
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = inflater.inflate(R.layout.notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bindData(notifications[position])
    }
    fun addItem(index:Int, notification:Notification){
        notifications.add(index, notification)
    }

}
class NotificationViewHolder(private var view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
    private lateinit var notificationTitle: TextView
    private lateinit var notificationBody: TextView
    private lateinit var notificationIcon: ImageView
    private var data: Notification? = null

    init{
        notificationTitle= view.findViewById(R.id.notification_title)
        notificationBody = view.findViewById(R.id.notification_body)
        notificationIcon = view.findViewById(R.id.notification_icon)

        view.setOnClickListener(this)
    }
    fun bindData(data:Notification){
        this.data = data
        notificationTitle.text = data.title
        notificationBody.text = data.infos
        notificationIcon.setImageResource(data.icon)

    }
    override fun onClick(v: View?){
        if(notificationTitle.text == "New Request"){
            TODO("Accept/dissmis")   
        }
        
    }
}