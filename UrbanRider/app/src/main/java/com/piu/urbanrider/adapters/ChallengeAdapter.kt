package com.piu.urbanrider.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.R
import com.piu.urbanrider.models.Challenge

class ChallengeAdapter(context: Context, private val challenges: ArrayList<Challenge>) :
    RecyclerView.Adapter<ChallengeViewHolder>() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val view = inflater.inflate(R.layout.challenge, parent, false)
        return ChallengeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return challenges.size
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        holder.bindData(challenges[position])
    }

    fun addItem(index: Int, challenge: Challenge) {
        challenges.add(index, challenge)
    }
}

class ChallengeViewHolder(view: View) : RecyclerView.ViewHolder(view),
    View.OnClickListener {
    private var challengeTitle: TextView
    private var challengeBody: TextView
    private var challengeIcon: ImageView
    private var challengePrize: TextView
    private var data: Challenge? = null

    init {
        challengeTitle = view.findViewById(R.id.ticket_title)
        challengeBody = view.findViewById(R.id.ticket_infos)
        challengeIcon = view.findViewById(R.id.ticket_icon)
        challengePrize = view.findViewById(R.id.challenge_prize)
        view.setOnClickListener(this)
    }

    fun bindData(data: Challenge) {
        this.data = data
        challengeTitle.text = data.title
        challengeBody.text = data.infos
        challengePrize.text = data.prize.toString()
        challengeIcon.setImageResource(data.icon)

    }

    override fun onClick(v: View?) {
        //What should we do here ?
    }
}