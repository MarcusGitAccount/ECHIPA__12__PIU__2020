package com.piu.urbanrider.models

import com.piu.urbanrider.R
import com.piu.urbanrider.models.Notification

class Notifications {
    private var notifications = ArrayList<Notification>()

    init{
        notifications = getTestNotifications();
    }

    fun getTestNotifications(): ArrayList<Notification>{
        val list = ArrayList<Notification>();
        list.add(
            Notification(
                1,
                "New request",
                "Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium dolorem..",
                R.drawable.new_request_sign
            )
        )
        list.add(
            Notification(
                2,
                "Ride finished",
                "Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium dolorem..",
                R.drawable.ride_finished_sign
            )
        )
        list.add(
            Notification(
                3,
                "New request",
                "Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium dolorem..",
                R.drawable.new_request_sign
            )
        )
        return list
    }
    fun getNotifications():ArrayList<Notification>{
        return notifications;
    }
}