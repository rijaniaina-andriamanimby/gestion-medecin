package com.example.medecinapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medecinapp.model.Medecin
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditDialog(
    medecin: Medecin? = null,
    onDismiss: () -> Unit,
    onConfirm: (Medecin) -> Unit
) {

    var nom by remember { mutableStateOf(medecin?.nom ?: "") }
    var nombreJour by remember { mutableStateOf(medecin?.nombre_jour?.toString() ?: "") }
    var tauxJournalier by remember { mutableStateOf(medecin?.taux_journalier?.toString() ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,

        containerColor = Color(0xFFF8FAFC),

        title = {

            Column {

                Text(
                    text = if (medecin == null)
                        "Ajouter un Médecin"
                    else
                        "Modifier Médecin",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF0F172A)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Informations personnelles et tarifaires",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        },

        text = {

            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                // CARD FORM
                Card(
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {

                        // NOM
                        OutlinedTextField(
                            value = nom,
                            onValueChange = { nom = it },
                            label = { Text("Nom du médecin") },
                            leadingIcon = {
                                Icon(Icons.Default.Person, null, tint = Color(0xFF2563EB))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium
                        )

                        // NOMBRE JOUR
                        OutlinedTextField(
                            value = nombreJour,
                            onValueChange = { nombreJour = it },
                            label = { Text("Nombre de jours") },
                            leadingIcon = {
                                Icon(Icons.Default.DateRange, null, tint = Color(0xFF10B981))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium
                        )

                        // TAUX
                        OutlinedTextField(
                            value = tauxJournalier,
                            onValueChange = { tauxJournalier = it },
                            label = { Text("Taux journalier (Ar)") },
                            leadingIcon = {
                                Icon(Icons.Default.TrendingUp, null, tint = Color(0xFFF59E0B))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium
                        )
                    }
                }
            }
        },

        confirmButton = {

            Button(
                onClick = {

                    val nouveau = Medecin(
                        nummed = medecin?.nummed,
                        nom = nom,
                        nombre_jour = nombreJour.toInt(),
                        taux_journalier = tauxJournalier.toInt()
                    )

                    onConfirm(nouveau)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF10B981)
                )
            ) {

                Icon(Icons.Default.Check, null)
                Spacer(modifier = Modifier.width(6.dp))
                Text(if (medecin == null) "Enregistrer" else "Modifier")
            }
        },

        dismissButton = {

            OutlinedButton(onClick = onDismiss) {

                Icon(Icons.Default.Close, null)
                Spacer(modifier = Modifier.width(6.dp))
                Text("Annuler")
            }
        }
    )
}