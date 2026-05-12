package com.example.medecinapp.ui

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.medecinapp.model.Stats
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

@Composable
fun PieChartView(stats: Stats) {

    AndroidView(

        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),

        factory = { context ->

            val chart = PieChart(context)

            // Données
            val entries = listOf(

                PieEntry(stats.min.toFloat(), "Min"),
                PieEntry(stats.max.toFloat(), "Max"),
                PieEntry(stats.total.toFloat(), "Total")
            )

            // Dataset
            val dataSet = PieDataSet(entries, "Prestations")

            dataSet.colors = listOf(
                Color.BLUE,
                Color.RED,
                Color.GREEN
            )

            // Espacement entre les parts
            dataSet.sliceSpace = 3f

            // Décalage sélection
            dataSet.selectionShift = 8f

            // Taille texte des valeurs
            dataSet.valueTextSize = 14f

            // Couleur texte des valeurs
            dataSet.valueTextColor = Color.WHITE

            // Data
            val data = PieData(dataSet)

            chart.data = data

            // Désactiver description
            chart.description.isEnabled = false

            // Texte central
            chart.centerText = "Prestations"

            chart.setCenterTextSize(18f)

            // Trou central
            chart.isDrawHoleEnabled = true

            chart.holeRadius = 45f

            // Animation
            chart.animateY(1000)

            // Taille labels
            chart.setEntryLabelTextSize(14f)

            // Couleur labels
            chart.setEntryLabelColor(Color.BLACK)

            // Légende
            chart.legend.textSize = 14f

            // Rotation tactile
            chart.isRotationEnabled = true

            chart.invalidate()

            chart
        }
    )
}