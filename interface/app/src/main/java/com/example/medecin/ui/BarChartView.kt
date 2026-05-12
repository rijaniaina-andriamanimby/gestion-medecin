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

            BarChart(context).apply {

                description.isEnabled = false
                animateY(1000)
                axisRight.isEnabled = false
                legend.textSize = 14f
                setFitBars(true)
            }
        },

        update = { chart ->

            val entries = listOf(
                BarEntry(0f, stats.min.toFloat()),
                BarEntry(1f, stats.max.toFloat()),
                BarEntry(2f, stats.total.toFloat())
            )

            val dataSet = BarDataSet(entries, "Prestations").apply {

                colors = listOf(
                    "#10B981".toColorInt(),
                    "#1E3A8A".toColorInt(),
                    "#B91C1C".toColorInt()
                )

                valueTextSize = 14f
            }

            val data = BarData(dataSet).apply {
                barWidth = 0.5f
            }

            chart.data = data

            val labels = listOf("Min", "Max", "Total")

            chart.xAxis.apply {
                valueFormatter = IndexAxisValueFormatter(labels)
                position = XAxis.XAxisPosition.BOTTOM
                granularity = 1f
                setDrawGridLines(false)
                textSize = 14f
            }

            chart.axisLeft.textSize = 14f

            chart.notifyDataSetChanged()
            chart.invalidate()
        }
    )
}