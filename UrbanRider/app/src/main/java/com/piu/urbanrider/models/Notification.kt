package com.piu.urbanrider.models

class Notification (var id:Int, var title:String, var infos:String, var icon: Int, var type: NotificationType) {

    enum class NotificationType {
        NEW_RIDE_REQUEST,
        RIDE_FINISHED,
        RIDE_PAYMENT
    }

}