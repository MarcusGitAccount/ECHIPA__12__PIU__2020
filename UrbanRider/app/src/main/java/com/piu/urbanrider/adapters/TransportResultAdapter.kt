package com.piu.urbanrider.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.R
import com.piu.urbanrider.models.TransportResult
import kotlinx.android.synthetic.main.layout_common_transport_result.*


class TransportResultAdapter (private val context : Context, private val transportResults : ArrayList<TransportResult>) : RecyclerView.Adapter<TransportResultAdapter.TransportResultViewHolder>() {

    private val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var viewType : Int = R.layout.layout_common_transport_result

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportResultViewHolder {
        val view = inflater.inflate(this.viewType, parent, false)
        return TransportResultViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transportResults.size
    }

    override fun onBindViewHolder(holder: TransportResultViewHolder, position: Int) {
        holder.bindData(transportResults[position])
    }

    override fun getItemId(position: Int) : Long {
        return super.getItemId(position)
    }

    fun addItem(index : Int, transportResult : TransportResult) {
        transportResults.add(index, transportResult)
    }

    fun removeItemAt(index: Int) {
        transportResults.removeAt(index)
    }

    fun clearItems() {
        transportResults.clear()
    }

    @SuppressLint("SetTextI18n")
    inner class TransportResultViewHolder(private var view : View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var transportResultTitle : TextView
        private lateinit var transportResultDescription : TextView
        private lateinit var transportResultImage : ImageView
        private lateinit var transportResultOrder : TextView
        private lateinit var transportResultCheckOcc : TextView
        private var data : TransportResult? = null
        private var pushState : Int = 0


        init {
            transportResultTitle = view.findViewById(R.id.layout_transport_result_title)
            transportResultDescription = view.findViewById(R.id.layout_transport_result_description)
            transportResultImage = view.findViewById(R.id.layout_transport_result_image)
            transportResultOrder = view.findViewById(R.id.layout_transport_result_order)

            when (viewType) {
                R.layout.layout_common_transport_result -> {
                    transportResultCheckOcc = view.findViewById(R.id.layout_transport_result_checkocc)
                    transportResultOrder.setOnClickListener {
                        val builder = AlertDialog.Builder(context, R.style.MyDialogTheme)

                        val ticketAlert : AlertDialog = builder.setTitle(R.string.string_ticket_notification)
                            .setPositiveButton(context.getString(R.string.string_dismiss), { ticketAlert, which -> ticketAlert.dismiss()})
                            .setMessage("Placed order for the selected line! Please check Tickets panel.")
                            .setCancelable(false)
                            .create()

                        ticketAlert.show()

                        ticketAlert.getButton(AlertDialog.BUTTON_POSITIVE).setBackgroundResource(R.color.urbanLightGreen)
                        ticketAlert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context.resources.getColor(R.color.white))
                        ticketAlert.getButton(AlertDialog.BUTTON_POSITIVE).setPadding(2, 0, 2, 0)
                        ticketAlert.setIcon(R.drawable.ic_option_baseline_ticket_24)

                    }

                    transportResultCheckOcc.setOnClickListener() {
                        val builder = AlertDialog.Builder(view.context, R.style.ModalTheme)
                        val builderView = LayoutInflater.from(view.context).inflate(R.layout.occ_modal_layout, null)

                        builder.setView(builderView)

                        val dialog = builder.create()

                        builderView.findViewById<TextView>(R.id.dialog_title).text = "Occupancy data for selected ride"
                        builderView.findViewById<TextView>(R.id.dialog_text1).text =
                            "Our approximations indicate a level of ${(30..60).random().toString()}% occupancy"
                        builderView.findViewById<TextView>(R.id.dialog_text2).text = "You will experience slight discomfort"

                        builderView.findViewById<Button>(R.id.modal_btn_dismiss).setOnClickListener() {
                            dialog.dismiss()
                        }
                        builderView.findViewById<Button>(R.id.modal_btn_accept).setOnClickListener() {
                            dialog.dismiss()
                        }
                        dialog.show()
                    }
                }
            }

            view.setOnClickListener(this)
        }

        override fun onClick(view : View?) {
        }

        fun bindData(data : TransportResult) {
            this.data = data

            when (viewType) {
                R.layout.layout_common_transport_result -> {
                    transportResultCheckOcc.setText(R.string.string_checkocc)
                }
            }

            transportResultTitle.text = data.title
            transportResultDescription.text = data.description
            transportResultImage.setImageResource(data.image)
            transportResultOrder.setText(R.string.string_order)
        }
    }
}
