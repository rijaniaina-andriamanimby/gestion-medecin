package com.example.medecinapp.ui

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.medecinapp.model.Stats
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*

@Composable
fun BarChartView(stats: Stats) {

    AndroidView(

        factory = { context ->

            val chart = BarChart(context)

            val entries = listOf(

                BarEntry(1f, stats.min.toFloat()),
                BarEntry(2f, stats.max.toFloat()),
                BarEntry(3f, stats.total.toFloat())
            )

            val dataSet = BarDataSet(entries, "Prestations")

            dataSet.colors = listOf(
                Color.BLUE,
                Color.RED,
                Color.GREEN
            )

            val data = BarData(dataSet)

            chart.data = data
            chart.description.isEnabled = false

            chart.invalidate()

            chart
        }
    )
}