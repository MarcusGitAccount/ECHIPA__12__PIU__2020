package Models

import com.piu.urbanrider.R

class Notifications {
    private var notifications = ArrayList<Notification>()

    init{
        getTestNotifications();
    }

    fun getTestNotifications(){
        val list = ArrayList<Notification>();
        list.add(Notification(1, "New request", "Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium dolorem..", R.drawable.new_request_sign))
        list.add(Notification(1, "Ride finished", "Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium dolorem..", R.drawable.ride_finished_sign))
        list.add(Notification(1, "New request", "Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium dolorem..", R.drawable.new_request_sign))
    }
}