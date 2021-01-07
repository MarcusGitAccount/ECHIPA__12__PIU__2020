package com.piu.urbanrider.models

import com.piu.urbanrider.R

class Challenges {
    private var challenges = ArrayList<Challenge>()

    init {
        challenges = getTestChallenges()
    }

    fun getTestChallenges(): ArrayList<Challenge> {
        val list = ArrayList<Challenge>()
        list.add(
            Challenge(
                1,
                "You and your bike",
                "Are you ready? You have 30 minutes to ride your bike and make 10km.",
                R.drawable.bike_challenge,
                40
            )
        )
        list.add(
            Challenge(
                2,
                "Walk, walk, walk",
                "If you reach 10 bus stations by walking, you can win a nice prize.",
                R.drawable.walk_challenge,
                100
            )
        )
        list.add(
            Challenge(
                3,
                "The best driver",
                "Be the best and get only 5 stars reviews in a day.",
                R.drawable.driver_challenge,
                250
            )
        )
        return list
    }

    fun getChallenges(): ArrayList<Challenge> {
        return challenges
    }
}