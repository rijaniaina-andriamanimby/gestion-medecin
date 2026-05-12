package com.example.medecinapp.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medecinapp.model.Medecin
import com.example.medecinapp.viewmodel.MedecinViewModel

@Composable
fun MedecinScreen(
    vm: MedecinViewModel = viewModel()
) {

    val context = LocalContext.current
    
    // Etat du dialog
    var showDialog by remember {
        mutableStateOf(false)
    }

    // Médecin sélectionné
    var selectedMedecin by remember {
        mutableStateOf<Medecin?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Bouton Ajouter
        Button(
            onClick = {

                selectedMedecin = null
                showDialog = true
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF10B981)
            )
        ) {

            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Ajouter"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Ajouter un Médecin")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Liste + statistiques
        LazyColumn {

            // Liste des médecins
            items(vm.medecins) { medecin ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Text(
                            text = "Nom : ${medecin.nom}",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Nombre Jour : ${medecin.nombre_jour}"
                        )

                        Text(
                            text = "Taux Journalier : ${medecin.taux_journalier}"
                        )

                        Text(
                            text = "Prestation : ${medecin.prestation}"
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {

                            // Modifier
                            Button(
                                onClick = {

                                    selectedMedecin = medecin
                                    showDialog = true
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Blue
                                )
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Modifier"
                                )

                                Spacer(modifier = Modifier.width(6.dp))

                                Text("Modifier")
                            }

                            // Supprimer
                            Button(
                                onClick = {

                                    medecin.nummed?.let {

                                        vm.deleteMedecin(it)

                                        Toast.makeText(
                                            context,
                                            "Médecin supprimé avec succès",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Red
                                )
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Supprimer"
                                )

                                Spacer(modifier = Modifier.width(6.dp))

                                Text("Supprimer")
                            }
                        }
                    }
                }
            }

            // SECTION STATISTIQUES
            item {

                vm.stats?.let { stats ->

                    Spacer(modifier = Modifier.height(20.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = "Statistiques",
                                style = MaterialTheme.typography.titleLarge
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text("Prestation minimale : ${stats.min}")

                            Text("Prestation maximale : ${stats.max}")

                            Text("Prestation totale : ${stats.total}")
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Histogramme",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    BarChartView(stats)

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Camembert",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    PieChartView(stats)

                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }

    // Dialog Ajouter / Modifier
    if (showDialog) {

        AddEditDialog(

            medecin = selectedMedecin,

            onDismiss = {

                showDialog = false
            },

            onConfirm = { medecin ->

                // AJOUT
                if (selectedMedecin == null) {

                    vm.addMedecin(medecin)

                    Toast.makeText(
                        context,
                        "Médecin ajouté avec succès",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    // MODIFICATION
                    medecin.nummed?.let {

                        vm.updateMedecin(
                            it,
                            medecin
                        )

                        Toast.makeText(
                            context,
                            "Médecin modifié avec succès",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                showDialog = false
            }
        )
    }
}