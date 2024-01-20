package com.example.trivialapp.model

import com.example.trivialapp.R

enum class Question(
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



