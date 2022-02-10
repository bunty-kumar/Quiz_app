package com.bunty.quizapp.activity.utils

import com.bunty.quizapp.R

object IconPicker {
    var icons = arrayOf(
             R.drawable.icon1,
             R.drawable.icon2,
             R.drawable.icon3,
             R.drawable.icon4,
             R.drawable.icon5,
             R.drawable.icon7
    )
    var currentIcon = 0

    fun geticon():Int{
        currentIcon = (currentIcon + 1) % icons.size
        return icons[currentIcon]
    }
}