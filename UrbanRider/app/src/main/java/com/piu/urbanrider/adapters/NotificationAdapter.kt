package com.piu.urbanrider.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.DrivingInterface
import com.piu.urbanrider.R
import com.piu.urbanrider.models.Notification

class NotificationAdapter(
    context: Context,
    private val notifications: ArrayList<Notification>
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

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

    fun addItem(index: Int, notification: Notification) {
        notifications.add(index, notification)
    }

    fun removeItem(position: Int) {
        notifications.removeAt(position)
        notifyDataSetChanged()
    }

    inner class NotificationViewHolder(private var view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private var notificationTitle: TextView = view.findViewById(R.id.notification_title)
        private var notificationBody: TextView = view.findViewById(R.id.notification_body)
        private var notificationIcon: ImageView = view.findViewById(R.id.notification_icon)
        private var data: Notification? = null

        init {
            view.setOnClickListener(this)
        }

        fun bindData(data: Notification) {
            this.data = data
            notificationTitle.text = data.title
            notificationBody.text = data.infos
            notificationIcon.setImageResource(data.icon)
        }

        @SuppressLint("SetTextI18n")
        override fun onClick(v: View?) {
            if (data?.type?.equals(Notification.NotificationType.NEW_RIDE_REQUEST)!!) {
                val builder = AlertDialog.Builder(view.context, R.style.ModalTheme)
                val builderView =
                    LayoutInflater.from(view.context).inflate(R.layout.modal_layout, null)

                builder.setView(builderView)

                builderView.findViewById<TextView>(R.id.dialog_title).text = "New ride request"
                builderView.findViewById<TextView>(R.id.dialog_text1).text = "Ion Ionescu"
                builderView.findViewById<TextView>(R.id.dialog_text2).text =
                    "Str. Clinicilor nr 12 Cluj-Napoca"

                val dialog = builder.create()

                val acceptBtn =
                    builderView.findViewById<Button>(R.id.modal_btn_accept).setOnClickListener() {
                        val intent = Intent(view.context, DrivingInterface::class.java)

                        startActivity(view.context, intent, null)
                        removeNotification(data!!)
                        dialog.dismiss()
                    }

                val dismissBtn =
                    builderView.findViewById<Button>(R.id.modal_btn_dismiss).setOnClickListener() {
                        removeNotification(data!!)
                        Toast.makeText(view.context, "Ride dismissed", Toast.LENGTH_LONG).show()
                        dialog.dismiss()
                    }

                dialog.show()
            }

        }

        fun removeNotification(data: Notification) {
            val item = notifications.find{x -> x.id == data.id}
            val position = notifications.indexOf(item)

            removeItem(position)
            notifyDataSetChanged()
            notifyItemRemoved(position)
        }
    }
}