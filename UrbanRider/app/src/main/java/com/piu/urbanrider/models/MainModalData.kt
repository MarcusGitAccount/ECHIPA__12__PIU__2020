package com.piu.urbanrider.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainModalData(
    var title: String? = "Notification",
    var content: String? = ""): Parcelable {
}