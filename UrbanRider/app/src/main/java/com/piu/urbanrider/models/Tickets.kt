package com.piu.urbanrider.models

import com.piu.urbanrider.R

class Tickets {
    private var tickets = ArrayList<Ticket>()

    init{
        tickets = getTestTickets();
    }

    fun getTestTickets(): ArrayList<Ticket>{
        val list = ArrayList<Ticket>();
        list.add(
            Ticket(
                1,
                "Ticket for line 24",
                "Ticket code: 2213csPo, Expires in 30 minutes.",
                R.drawable.ticket_icon,
                "12:28PM"
            )
        )
        list.add(
            Ticket(
                2,
                "Ticket for line 24",
                "Ticket code: 2213csPo, Expires in 30 minutes.",
                R.drawable.ticket_icon,
                "12:28PM"
            )
        )
        list.add(
            Ticket(
                3,
                "Ticket for line 24",
                "Ticket code: 2213csPo, Expires in 30 minutes.",
                R.drawable.ticket_icon,
                "12:28PM"
            )
        )
        return list
    }
    fun getTickets():ArrayList<Ticket>{
        return tickets;
    }
}