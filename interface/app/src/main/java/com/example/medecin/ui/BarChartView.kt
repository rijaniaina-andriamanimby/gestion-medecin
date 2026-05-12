package com.example.medecinapp.ui

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.medecinapp.model.Stats
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import androidx.core.graphics.toColorInt

@Composable
fun BarChartView(stats: Stats) {

    AndroidView(

        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),

        factory = { context ->

            val chart = BarChart(context)

            val entries = listOf(

                BarEntry(0f, stats.min.toFloat()),
                BarEntry(1f, stats.max.toFloat()),
                BarEntry(2f, stats.total.toFloat())
            )

            val dataSet = BarDataSet(entries, "Prestations")

            dataSet.colors = listOf(
                "#10B981".toColorInt(), // Emerald
                "#1E3A8A".toColorInt(), // Blue foncé
                "#B91C1C".toColorInt()  // Red foncé
            )

            // Taille des valeurs sur les barres
            dataSet.valueTextSize = 14f

            val data = BarData(dataSet)

            // Largeur des barres
            data.barWidth = 0.5f

            chart.data = data

            // Désactiver description
            chart.description.isEnabled = false

            // Animation
            chart.animateY(1000)

            // Taille légende
            chart.legend.textSize = 14f

            // Axe X
            val labels = listOf(
                "Min",
                "Max",
                "Total"
            )

            val xAxis = chart.xAxis

            xAxis.valueFormatter =
                IndexAxisValueFormatter(labels)

            xAxis.position = XAxis.XAxisPosition.BOTTOM

            xAxis.granularity = 1f

            xAxis.setDrawGridLines(false)

            xAxis.textSize = 14f

            // Axe gauche
            chart.axisLeft.textSize = 14f

            // Axe droit désactivé
            chart.axisRight.isEnabled = false

            chart.setFitBars(true)

            chart.invalidate()

            chart
        }
    )
}