package com.example.trivialapp.model

import com.example.trivialapp.R

enum class easyQuestions(
    val img: Int,
    val answers: Array<String>,
    val raightAnswear: String
) {
    palaMalasHierbas(R.drawable.para_mals_hierbas0, arrayOf(
        "Extraer malas hierbas",
        "Extraer cucarachas",
        "Regar las plantas",
        "Desplazar rocas"),
        "Extraer malas hierbas"
    ),
    podadora(R.drawable.podadora1, arrayOf(
        "Llegar a las macetas del segundo piso",
        "Podar de arboles",
        "Desplatar flores",
        "Podar de césped"),
        "Podar de arboles"),
    tijerasHardineria(R.drawable.tijeras_hardineria3, arrayOf(
        "Cortar el panal",
        "Cocinar",
        "Papiroflexia",
        "Jardineria"),
        "Jardineria")
}

/*
enum class normalQuestions(
    val img: Int,
    val question: String,
    val answers: Array<String>,
    val raightAnswear: String
) {
    babyGoat(
        R.drawable.mediu1,
        "What is a baby goat called?",
        arrayOf("Cabrito", "Goti", "Small sheep", "Kid"),
        "Kid"
    ),
    hannahMontana(
        R.drawable.midium2,
        "What was the name of Miley Cyrus's Disney character?",
        arrayOf("Jessie", "Hannah Montana", "Bella Thorne", "Cenicienta"),
        "Hannah Montana"
    ),
    brieCheese(
        R.drawable.mediun3,
        "What country is Brie cheese originally from",
        arrayOf("Spain", "Germany", "France", "Luxembourg"),
        "France"
    )
}

enum class difficultQuestions(
    val img: Int,
    val question: String,
    val answers: Array<String>,
    val raightAnswear: String
) {
    nationalFlags(
        R.drawable.difficult1,
"What color do about 75 percent of national flags contain?",
        arrayOf("Blue", "Red", "Green", "Yellow"),
        "Red"
    ),
    weimarRerpublic(
        R.drawable.difficult2,
        "From 1919 to 1933, the Weimar Republic was the government of what nation?",
        arrayOf("Russia", "Germany", "France", "Ukraine"),
        "Germany"
    ),
    erosionRock(
        R.drawable.difficult3,
        "What is the wearing away of rock by wind, water or gravity called?",
        arrayOf("Declension", "Erosion", "Grating", "Silting"),
        "Erosion"
    )
}

 */



