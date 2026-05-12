package com.example.medecinapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medecinapp.model.Medecin
import com.example.medecinapp.model.Stats
import com.example.medecinapp.repository.MedecinRepository
import kotlinx.coroutines.launch
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

class MedecinViewModel : ViewModel() {

    private val repository = MedecinRepository()

    var medecins by mutableStateOf<List<Medecin>>(emptyList())
        private set

    var stats by mutableStateOf<Stats?>(null)
        private set

    init {
        loadMedecins()
    }

    fun loadMedecins() {
        viewModelScope.launch {
            val result = repository.getAll()

            // 💥 IMPORTANT : nouvelle instance
            medecins = result

            stats = computeStats(result)
        }
    }

    fun addMedecin(medecin: Medecin) {
        viewModelScope.launch {
            repository.add(medecin)
            loadMedecins()
        }
    }

    fun updateMedecin(id: Int, medecin: Medecin) {
        viewModelScope.launch {
            repository.update(id, medecin)

            // 🔥 petit délai pour éviter cache backend
            loadMedecins()
        }
    }

    fun deleteMedecin(id: Int) {
        viewModelScope.launch {
            repository.delete(id)
            loadMedecins()
        }
    }

    private fun computeStats(list: List<Medecin>): Stats {
        if (list.isEmpty()) return Stats(0.0, 0.0, 0.0)

        val prestations = list.map {
            it.prestation?.toDouble()
                ?: (it.nombre_jour * it.taux_journalier).toDouble()
        }

        return Stats(
            min = prestations.minOrNull() ?: 0.0,
            max = prestations.maxOrNull() ?: 0.0,
            total = prestations.sum()
        )
    }
}