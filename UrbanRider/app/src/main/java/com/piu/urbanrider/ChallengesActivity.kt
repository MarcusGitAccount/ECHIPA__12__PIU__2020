package com.piu.urbanrider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.piu.urbanrider.adapters.ChallengeAdapter
import com.piu.urbanrider.models.Challenges

class ChallengesActivity : AppCompatActivity() {

    var challengeAdapter: ChallengeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenges)
        val recyclerRef = findViewById<RecyclerView>(R.id.notification_rv)
        challengeAdapter = ChallengeAdapter(this@ChallengesActivity, Challenges().getChallenges())
        recyclerRef.adapter = challengeAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerRef.layoutManager = linearLayoutManager
    }
}