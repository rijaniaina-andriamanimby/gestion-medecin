package com.example.medecinapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medecinapp.model.Medecin
import com.example.medecinapp.repository.MedecinRepository
import kotlinx.coroutines.launch

class MedecinViewModel : ViewModel() {

    private val repository = MedecinRepository()

    var medecins = mutableStateListOf<Medecin>()
    var stats by mutableStateOf<Stats?>(null)

    init {
        loadMedecins()
        loadStats()
    }

    fun loadMedecins() {
        viewModelScope.launch {
            medecins.clear()
            medecins.addAll(repository.getAll())
        }
    }

    fun addMedecin(medecin: Medecin) {
        viewModelScope.launch {
            repository.add(medecin)
            loadMedecins()
            loadStats()
        }
    }

    fun updateMedecin(id: Int, medecin: Medecin) {
        viewModelScope.launch {
            repository.update(id, medecin)
            loadMedecins()
            loadStats()
        }
    }

    fun deleteMedecin(id: Int) {
        viewModelScope.launch {
            repository.delete(id)
            loadMedecins()
            loadStats()
        }
    }

    fun loadStats() {
        viewModelScope.launch {
            stats = repository.getStats()
        }
    }
}