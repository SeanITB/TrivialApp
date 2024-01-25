package com.example.trivialapp.model

import com.example.trivialapp.R

enum class Question(
    val difficulty: Char,
    var isDone: Boolean,
    val img: Int,
    val question: String,
    val answers: Array<String>,
    val raightAnswear: String
) {
    palaMalasHierbas(
        'E',
        false,
        R.drawable.easy1,
        "Hitters use a bat while catchers use a mitt in which sport?",
        arrayOf(
        "Baseball",
        "Kendo",
        "Cricket",
        "Field hockey"),
        "Baseball"
    ),
    podadora(
        'E',
        false,
        R.drawable.easy2,
        "Players need to wear these helmets and shoulder pads for which game?",
        arrayOf(
        "Harness racing",
        "High jump",
        "Deadlifting",
        "Football"),
        "Football"
    ),
    tijerasHardineria(
        'E',
        false,
        R.drawable.easy3,
        "A cue stick needs cue chalk for which sport?\n",
        arrayOf(
        "Darts",
        "Billiards",
        "Croquet",
        "Polo"),
        "Billiards"
    ),
    babyGoat(
        'N',
        false,
        R.drawable.mediu1,
        "What is a baby goat called?",
        arrayOf("Cabrito", "Goti", "Small sheep", "Kid"),
        "Kid"
    ),
    hannahMontana(
        'N',
        false,
        R.drawable.midium2,
        "What was the name of Miley Cyrus's Disney character?",
        arrayOf("Jessie", "Hannah Montana", "Bella Thorne", "Cenicienta"),
        "Hannah Montana"
    ),
    brieCheese(
        'N',
        false,
        R.drawable.mediun3,
        "What country is Brie cheese originally from",
        arrayOf("Spain", "Germany", "France", "Luxembourg"),
        "France"
    ),
    nationalFlags(
        'D',
        false,
        R.drawable.difficult1,
        "What color do about 75 percent of national flags contain?",
        arrayOf("Blue", "Red", "Green", "Yellow"),
        "Red"
    ),
    weimarRerpublic(
        'D',
        false,
        R.drawable.difficult2,
        "From 1919 to 1933, the Weimar Republic was the government of what nation?",
        arrayOf("Russia", "Germany", "France", "Ukraine"),
        "Germany"
    ),
    erosionRock(
        'D',
        false,
        R.drawable.difficult3,
        "What is the wearing away of rock by wind, water or gravity called?",
        arrayOf("Declension", "Erosion", "Grating", "Silting"),
        "Erosion"
    ),
    vienna(
        'D',
        false,
        R.drawable.difficult4,
        "Vienna is the capital of what country?",
        arrayOf("Austria", "Germany", "Bulgaria", "Montenegro"),
        "Austria"
    ),
    earthLayers(
        'D',
        false,
        R.drawable.difficult5,
        "Which of these is NOT one of the Earth's layers?",
        arrayOf("Core", "Crust", "Mantel", "Terrestria"),
        "Terrestria"
    )
}




