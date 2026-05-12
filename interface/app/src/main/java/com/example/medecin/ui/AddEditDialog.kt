package com.example.medecinapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medecinapp.model.Medecin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditDialog(

    medecin: Medecin? = null,

    onDismiss: () -> Unit,

    onConfirm: (Medecin) -> Unit

) {

    var nom by remember {
        mutableStateOf(medecin?.nom ?: "")
    }

    var nombreJour by remember {
        mutableStateOf(
            medecin?.nombre_jour?.toString() ?: ""
        )
    }

    var tauxJournalier by remember {
        mutableStateOf(
            medecin?.taux_journalier?.toString() ?: ""
        )
    }

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {

            Text(
                if (medecin == null)
                    "Ajouter Médecin"
                else
                    "Modifier Médecin"
            )
        },

        text = {

            Column {

                OutlinedTextField(
                    value = nom,
                    onValueChange = {
                        nom = it
                    },
                    label = {
                        Text("Nom")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = nombreJour,
                    onValueChange = {
                        nombreJour = it
                    },
                    label = {
                        Text("Nombre de jour")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = tauxJournalier,
                    onValueChange = {
                        tauxJournalier = it
                    },
                    label = {
                        Text("Taux journalier")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },

        confirmButton = {

            Button(

                onClick = {

                    val nouveauMedecin = Medecin(

                        nummed = medecin?.nummed,

                        nom = nom,

                        nombre_jour = nombreJour.toInt(),

                        taux_journalier = tauxJournalier.toDouble()
                    )

                    onConfirm(nouveauMedecin)
                }

            ) {

                Text(
                    if (medecin == null)
                        "Ajouter"
                    else
                        "Modifier"
                )
            }
        },

        dismissButton = {

            OutlinedButton(
                onClick = onDismiss
            ) {

                Text("Annuler")
            }
        }
    )
}