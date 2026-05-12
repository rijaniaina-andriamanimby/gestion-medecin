package com.example.medecinapp.model

data class Medecin(
    val nummed: Int? = null,
    val nom: String,
    val nombre_jour: Int,
    val taux_journalier: Double,
    val prestation: Double? = null
)