package com.bunty.quizapp.activity.utils

object ColorPicker {
    var colors = arrayOf(
        "#3E89DF",
        "#3685BC",
        "#D36280",
        "#E44F55",
        "#FA8056",
        "#818BCA",
        "#7D659F",
        "#51BAB3",
        "#4F866C",
        "#E3AD17",
        "#627991",
        "#EF8EAD",
        "#B5BFC6"
    )
    var currentColorIndex = 0

    fun getcolor():String{
        currentColorIndex = (currentColorIndex + 1) % colors.size
        return colors[currentColorIndex]
    }
}