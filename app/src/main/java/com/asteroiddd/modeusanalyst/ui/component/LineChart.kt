package com.asteroiddd.modeusanalyst.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asteroiddd.modeusanalyst.ui.theme.White
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

@OptIn(ExperimentalTextApi::class)
@Composable
fun LineChart(points: List<Float>, modifier: Modifier = Modifier) {
    if (points.isEmpty()) return

    val textMeasurer = rememberTextMeasurer()
    val yAxisWidth = 56.dp
    val xAxisHeight = 30.dp
    val xStep = 64.dp
    val tickLength = 10.dp

    val (axisMin, axisMax, interval) = remember(points) {
        val yMax = points.maxOrNull() ?: 0f
        val yMin = points.minOrNull() ?: 0f

        val rangeMax = (yMax + 2f).coerceAtLeast(0f)
        val rangeMin = yMin.coerceAtMost(0f)

        val range = (rangeMax - rangeMin).let { if (it <= 0f) 1f else it }
        val roughInterval = range / 5
        val exponent = floor(log10(roughInterval))
        val powerOf10 = 10.0.pow(exponent.toDouble()).toFloat()
        val mantissa = roughInterval / powerOf10
        val niceMantissa = when {
            mantissa > 5 -> 10f
            mantissa > 2 -> 5f
            mantissa > 1 -> 2f
            else -> 1f
        }
        val niceInterval = niceMantissa * powerOf10

        val finalAxisMax = ceil(rangeMax / niceInterval) * niceInterval
        val finalAxisMin = floor(rangeMin / niceInterval) * niceInterval
        Triple(finalAxisMin, finalAxisMax, niceInterval)
    }
    val axisRange = (axisMax - axisMin).let { if (it == 0f) 1f else it }

    Row(modifier = modifier.fillMaxWidth().height(250.dp).padding(8.dp)) {
        Canvas(modifier = Modifier.width(yAxisWidth).fillMaxHeight()) {
            val canvasHeight = size.height - xAxisHeight.toPx()
            val labelCount = ((axisMax - axisMin) / interval).toInt() + 1

            for (i in 0 until labelCount) {
                val value = axisMin + i * interval
                val y = canvasHeight - ((value - axisMin) / axisRange * canvasHeight)
                if (y in 0f..canvasHeight) {
                    drawText(
                        textMeasurer = textMeasurer,
                        text = "%.2f".format(value),
                        topLeft = Offset(0f, y - 8.sp.toPx()),
                        style = TextStyle(fontSize = 12.sp, color = White)
                    )
                    drawLine(
                        color = White.copy(alpha = 0.5f),
                        start = Offset(size.width - tickLength.toPx(), y),
                        end = Offset(size.width, y),
                        strokeWidth = 2f
                    )
                }
            }
            drawLine(
                color = White.copy(alpha = 0.5f),
                start = Offset(size.width, 0f),
                end = Offset(size.width, canvasHeight),
                strokeWidth = 2f
            )
        }

        Box(modifier = Modifier.weight(1f).fillMaxHeight().horizontalScroll(rememberScrollState())) {
            Canvas(modifier = Modifier.width(xStep * points.size).fillMaxHeight()) {
                val canvasHeight = size.height - xAxisHeight.toPx()
                val xStepPx = xStep.toPx()

                (0..((axisMax - axisMin) / interval).toInt()).forEach {
                    val y = canvasHeight - (it * interval / axisRange) * canvasHeight
                    if (y in 0f..canvasHeight) {
                        drawLine(
                            color = White.copy(alpha = 0.2f),
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = 1f
                        )
                    }
                }

                drawLine(
                    color = White.copy(alpha = 0.5f),
                    start = Offset(0f, canvasHeight),
                    end = Offset(size.width, canvasHeight),
                    strokeWidth = 2f
                )

                if (points.size > 1) {
                    val path = Path().apply {
                        moveTo(0f, canvasHeight - ((points.first() - axisMin) / axisRange * canvasHeight))
                        points.drop(1).forEachIndexed { i, point ->
                            lineTo((i + 1) * xStepPx, canvasHeight - ((point - axisMin) / axisRange * canvasHeight))
                        }
                    }
                    drawPath(path = path, color = White, style = Stroke(width = 5f))
                }

                points.forEachIndexed { i, point ->
                    val x = i * xStepPx
                    val y = canvasHeight - ((point - axisMin) / axisRange * canvasHeight)
                    drawCircle(color = White, radius = 8f, center = Offset(x, y))

                    val textValue = point.toInt().toString()
                    val textLayout = textMeasurer.measure(
                        text = textValue,
                        style = TextStyle(fontSize = 12.sp, color = White)
                    )
                    val textX = if (i == 0) x + 4.dp.toPx() else x - textLayout.size.width / 2
                    drawText(
                        textLayoutResult = textLayout,
                        topLeft = Offset(textX, y - 8f - textLayout.size.height - 4.dp.toPx())
                    )

                    drawLine(
                        color = White.copy(alpha = 0.5f),
                        start = Offset(x, canvasHeight),
                        end = Offset(x, canvasHeight + tickLength.toPx()),
                        strokeWidth = 2f
                    )
                }
            }
        }
    }
}
