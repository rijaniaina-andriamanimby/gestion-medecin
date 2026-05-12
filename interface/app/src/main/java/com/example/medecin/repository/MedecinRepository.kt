package com.example.medecinapp.repository

import com.example.medecinapp.api.RetrofitClient
import com.example.medecinapp.model.Medecin

class MedecinRepository {

    suspend fun getAll() =
        RetrofitClient.api.getMedecins()

    suspend fun add(medecin: Medecin) =
        RetrofitClient.api.addMedecin(medecin)

    suspend fun update(id: Int, medecin: Medecin) =
        RetrofitClient.api.updateMedecin(id, medecin)

    suspend fun delete(id: Int) =
        RetrofitClient.api.deleteMedecin(id)

    suspend fun getStats() =
        RetrofitClient.api.getStats()
}