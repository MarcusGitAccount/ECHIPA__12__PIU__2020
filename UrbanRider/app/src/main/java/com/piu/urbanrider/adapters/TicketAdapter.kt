package com.piu.urbanrider.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.R
import com.piu.urbanrider.models.Ticket

class TicketAdapter(private val context: Context, private val tickets: ArrayList<Ticket>):RecyclerView.Adapter<TicketViewHolder>() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val view = inflater.inflate(R.layout.ticket, parent, false)
        return TicketViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tickets.size
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.bindData(tickets[position])
    }
    fun addItem(index:Int, ticket:Ticket){
        tickets.add(index, ticket)
    }
}

class TicketViewHolder(private var view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
    private lateinit var ticketTitle: TextView
    private lateinit var ticketBody: TextView
    private lateinit var ticketTimestamp: TextView
    private lateinit var ticketIcon: ImageView
    private var data: Ticket? = null

    init{
        ticketTitle= view.findViewById(R.id.ticket_title)
        ticketBody = view.findViewById(R.id.ticket_infos)
        ticketIcon = view.findViewById(R.id.ticket_icon)
        ticketTimestamp = view.findViewById(R.id.ticket_timestamp)

        view.setOnClickListener(this)
    }
    fun bindData(data: Ticket){
        this.data = data
        ticketTitle.text = data.title
        ticketBody.text = data.infos
        ticketTimestamp.text = data.timestamp
        ticketIcon.setImageResource(data.icon)

    }
    override fun onClick(v: View?) {
        TODO("Accept/dissmis")
    }
}