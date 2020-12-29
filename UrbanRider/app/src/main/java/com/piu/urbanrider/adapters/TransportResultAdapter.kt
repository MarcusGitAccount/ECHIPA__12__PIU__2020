package com.piu.urbanrider.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.R
import com.piu.urbanrider.models.TransportResult
import kotlinx.android.synthetic.main.layout_common_transport_result.*


class TransportResultAdapter (private val context : Context, private val transportResults : ArrayList<TransportResult>) : RecyclerView.Adapter<TransportResultAdapter.TransportResultViewHolder>() {

    private val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var viewType : Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportResultViewHolder {
        val view = inflater.inflate(viewType, parent, false)
        return TransportResultViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transportResults.size
    }

    override fun onBindViewHolder(holder: TransportResultViewHolder, position: Int) {
        holder.bindData(transportResults[position])
    }

    inner class TransportResultViewHolder(private var view : View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var transportResultTitle : TextView
        private lateinit var transportResultDescription : TextView
        private lateinit var transportResultImage : ImageView
        private lateinit var transportResultOrder : TextView
        private var data : TransportResult? = null
        private var pushState : Int = 0

        init {
            transportResultTitle = view.findViewById(R.id.layout_transport_result_title)
            transportResultDescription = view.findViewById(R.id.layout_transport_result_description)
            transportResultImage = view.findViewById(R.id.layout_transport_result_image)
            transportResultOrder = view.findViewById(R.id.layout_transport_result_order)
            view.setOnClickListener(this)
        }

        override fun onClick(view : View?) {
        }

        fun bindData(data : TransportResult) {
            this.data = data

            transportResultTitle.text = data.title
            transportResultDescription.text = data.description
            transportResultImage.setImageResource(data.image)
            transportResultOrder.text = R.string.string_order.toString();
        }
    }
}
