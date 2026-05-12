package com.example.medecinapp.ui

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.toColorInt
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

            PieChart(context).apply {

                description.isEnabled = false
                isDrawHoleEnabled = true
                holeRadius = 45f
                setCenterTextSize(18f)
                centerText = "Prestations"
                animateY(1000)
                setEntryLabelTextSize(14f)
                setEntryLabelColor(Color.BLACK)
                legend.textSize = 14f
                isRotationEnabled = true
            }
        },

        update = { chart ->

            val entries = listOf(
                PieEntry(stats.min.toFloat(), "Min"),
                PieEntry(stats.max.toFloat(), "Max"),
                PieEntry(stats.total.toFloat(), "Total")
            )

            val dataSet = PieDataSet(entries, "Prestations").apply {

                colors = listOf(
                    "#10B981".toColorInt(),
                    "#1E3A8A".toColorInt(),
                    "#B91C1C".toColorInt()
                )

                sliceSpace = 3f
                selectionShift = 8f
                valueTextSize = 14f
                valueTextColor = Color.WHITE
            }

            chart.data = PieData(dataSet)

            chart.notifyDataSetChanged()
            chart.invalidate()
        }
    )
}