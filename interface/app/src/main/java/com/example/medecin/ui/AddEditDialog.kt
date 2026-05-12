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

        title = {
            Text(
                if (medecin == null) "Ajouter Médecin"
                else "Modifier Médecin"
            )
        },

        text = {

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                // NOM
                OutlinedTextField(
                    value = nom,
                    onValueChange = { nom = it },
                    label = { Text("Nom") },
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = null)
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                        autoCorrect = false
                    )
                )

                // NOMBRE JOUR
                OutlinedTextField(
                    value = nombreJour,
                    onValueChange = { nombreJour = it },
                    label = { Text("Nombre de jour") },
                    leadingIcon = {
                        Icon(Icons.Default.DateRange, contentDescription = null)
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                        autoCorrect = false
                    )
                )

                // TAUX JOURNALIER
                OutlinedTextField(
                    value = tauxJournalier,
                    onValueChange = { tauxJournalier = it },
                    label = { Text("Taux journalier") },
                    leadingIcon = {
                        Icon(Icons.Default.Star, contentDescription = null)
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                        autoCorrect = false
                    )
                )
            }
        },

        confirmButton = {

            Button(
                onClick = {

                    val nouveau = Medecin(
                        nummed = medecin?.nummed,
                        nom = nom,
                        nombre_jour = nombreJour.toInt(),
                        taux_journalier = tauxJournalier.toDouble()
                    )

                    onConfirm(nouveau)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF10B981)
                )
            ) {

                Icon(Icons.Default.Check, contentDescription = null)
                Spacer(modifier = Modifier.width(6.dp))
                Text(if (medecin == null) "Ajouter" else "Modifier")
            }
        },

        dismissButton = {

            OutlinedButton(onClick = onDismiss) {

                Icon(Icons.Default.Close, contentDescription = null)
                Spacer(modifier = Modifier.width(6.dp))
                Text("Annuler")
            }
        }
    )
}