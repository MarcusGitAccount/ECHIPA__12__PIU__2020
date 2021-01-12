package com.piu.urbanrider.models

import com.piu.urbanrider.R

class DrawerOptions {
    private var drawerOptions = ArrayList<DrawerOption>()

    init {
        drawerOptions = getUserOptions()
    }

    fun getUserOptions(): ArrayList<DrawerOption> {
        val list = ArrayList<DrawerOption>()
        list.add(
            DrawerOption(
                1,
                "Reviews",
                R.drawable.ic_option_baseline_star_border_24
            )
        )
        list.add(
            DrawerOption(
                1,
                "Notifications",
                R.drawable.ic_option_baseline_notifications_none_24
            )
        )
        list.add(
            DrawerOption(
                1,
                "Tickets",
                R.drawable.ic_option_baseline_ticket_24
            )
        )
        list.add(
            DrawerOption(
                1,
                "Settings",
                R.drawable.ic_option_baseline_settings_24
            )
        )
        list.add(
            DrawerOption(
                1,
                "Challenges",
                R.drawable.ic_option_baseline_local_activity_24
            )
        )
        list.add(
            DrawerOption(
                1,
                "Share eco equipment",
                R.drawable.ic_option_baseline_directions_bike_24
            )
        )
        list.add(
            DrawerOption(
                1,
                "Dashboard",
                R.drawable.ic_option_baseline_dashboard_24
            )
        )
        list.add(
            DrawerOption(
                1,
                "Logout",
                R.drawable.ic_baseline_logout_24
            )
        )

        return list
    }

    fun getDrawerOptions(): ArrayList<DrawerOption> {
        return this.drawerOptions
    }
}