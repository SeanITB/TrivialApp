package com.example.trivialapp.model

import com.example.trivialapp.R

enum class Questions(
    val img: Int,
    val answers: Array<String>
) {
    palaMalasHierbas(R.drawable.para_mals_hierbas0, arrayOf(
        "Extraer malas hierbas",
        "Extraer cucarachas",
        "Regar las plantas",
        "Desplazar rocas")),
    podadora(R.drawable.podadora1, arrayOf(
        "Llegar a las macetas del segundo piso",
        "Podar de arboles",
        "Desplatar flores",
        "Podar de césped"
    )),
    tijerasHardineria(R.drawable.tijeras_hardineria3, arrayOf(
        "Cortar el cordel del panal",
        "Cocinar",
        "Papiroflexia",
        "Jardineria"))
}

data class Settings (
    val difficulty: String,
    val rounds: Int,
    val time: Int,
    //val darkMode: Boolean
)

