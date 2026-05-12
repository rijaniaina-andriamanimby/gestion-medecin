package com.example.medecinapp.ui

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.medecinapp.model.Stats
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*

@Composable
fun PieChartView(stats: Stats) {

    AndroidView(

        factory = { context ->

            val chart = PieChart(context)

            val entries = listOf(

                PieEntry(stats.min.toFloat(), "Min"),
                PieEntry(stats.max.toFloat(), "Max"),
                PieEntry(stats.total.toFloat(), "Total")
            )

            val dataSet = PieDataSet(entries, "Prestations")

            dataSet.colors = listOf(
                Color.BLUE,
                Color.RED,
                Color.GREEN
            )

            val data = PieData(dataSet)

            chart.data = data
            chart.description.isEnabled = false

            chart.invalidate()

            chart
        }
    )
}