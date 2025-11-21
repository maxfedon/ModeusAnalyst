package com.asteroiddd.modeusanalyst.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.asteroiddd.modeusanalyst.ui.theme.Typography
import com.asteroiddd.modeusanalyst.ui.theme.White
import kotlin.math.max

data class PieChartData(val label: String, val value: Float)

@Composable
fun PieChart(modifier: Modifier = Modifier, data: List<PieChartData>) {
    if (data.isEmpty()) return

    val sortedData = remember(data) {
        data.sortedWith(compareByDescending<PieChartData> { it.value > 0 }.thenBy { it.label })
    }
    val chartData = remember(sortedData) {
        sortedData.map { it.copy(value = max(it.value, 1f)) }
    }
    val totalValue = remember(chartData) { chartData.sumOf { it.value.toDouble() }.toFloat() }
    val colors = remember(sortedData) { generateColors(sortedData.size) }

    Row(
        modifier = modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier = Modifier.size(150.dp)) {
            var startAngle = -90f
            chartData.forEachIndexed { index, item ->
                val sweepAngle = (item.value / totalValue) * 360f
                val sliceColor = if (sortedData[index].value == 0f) White else colors[index]
                drawArc(
                    color = sliceColor,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    size = Size(size.width, size.height)
                )
                startAngle += sweepAngle
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Legend(data = sortedData, colors = colors)
    }
}

@SuppressLint("DefaultLocale")
@Composable
private fun Legend(modifier: Modifier = Modifier, data: List<PieChartData>, colors: List<Color>) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        data.forEachIndexed { index, item ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                val legendColor = if (item.value == 0f) White else colors[index]
                Box(modifier = Modifier.size(10.dp).background(legendColor))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${item.label} (${String.format("%.2f", item.value)})",
                    style = Typography.bodySmall,
                    color = White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

private fun generateColors(count: Int): List<Color> = List(count) { i ->
    Color.hsv(hue = (360f / count) * i, saturation = 0.8f, value = 0.9f)
}
