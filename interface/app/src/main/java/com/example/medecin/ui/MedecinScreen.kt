package com.example.medecinapp.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
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

        // HEADER
        Text(
            text = "Gestion des Médecins",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF0F172A)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Administration et statistiques",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

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
                        .padding(vertical = 8.dp),

                    shape = MaterialTheme.shapes.large,

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8FAFC)
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        // NOM + BADGE
                        Row(
                            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = Color(0xFF10B981)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Column {

                                Text(
                                    text = medecin.nom,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color(0xFF0F172A)
                                )

                                Spacer(modifier = Modifier.height(4.dp))

//                                Text(
//                                    text = "Médecin",
//                                    color = Color.Gray,
//                                    style = MaterialTheme.typography.bodySmall
//                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // INFOS
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFF1F5F9)
                            )
                        ) {

                            Column(
                                modifier = Modifier.padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(14.dp)
                            ) {

                                // NOMBRE DE JOUR
                                Row(
                                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                                ) {

                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = null,
                                        tint = Color(0xFF2563EB)
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Text(
                                        text = "Nombre de jours : ${medecin.nombre_jour}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }

                                // TAUX JOURNALIER
                                Row(
                                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                                ) {

                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = Color(0xFFF59E0B)
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Text(
                                        text = "Taux journalier : ${medecin.taux_journalier} Ar",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }

                                HorizontalDivider()

                                // PRESTATION
                                Row(
                                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                                ) {

                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = null,
                                        tint = Color(0xFF10B981)
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Text(
                                        text = "Prestation : ${medecin.prestation} Ar",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = Color(0xFF10B981)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // BOUTONS
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            Button(
                                onClick = {

                                    selectedMedecin = medecin
                                    showDialog = true
                                },
                                modifier = Modifier.weight(1f),

                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF2563EB)
                                )
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = null
                                )

                                Spacer(modifier = Modifier.width(6.dp))

                                Text("Modifier")
                            }

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

                                modifier = Modifier.weight(1f),

                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFDC2626)
                                )
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),

                        shape = MaterialTheme.shapes.large,

                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),

                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF8FAFC)
                        )
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