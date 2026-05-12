package com.example.medecinapp.api

import com.example.medecinapp.model.Medecin
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("medecins")
    suspend fun getMedecins(): List<Medecin>

    @POST("medecins")
    suspend fun addMedecin(
        @Body medecin: Medecin
    ): Response<Medecin>

    @PUT("medecins/{id}")
    suspend fun updateMedecin(
        @Path("id") id: Int,
        @Body medecin: Medecin
    ): Response<List<Medecin>>

    @DELETE("medecins/{id}")
    suspend fun deleteMedecin(
        @Path("id") id: Int
    ): Response<Unit>
}