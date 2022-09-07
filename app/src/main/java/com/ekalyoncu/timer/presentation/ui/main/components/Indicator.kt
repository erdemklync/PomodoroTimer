package com.ekalyoncu.timer.presentation.ui.main.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ekalyoncu.timer.presentation.theme.Purple40
import com.ekalyoncu.timer.presentation.theme.Purple80
import com.ekalyoncu.timer.util.TimeConverter
import kotlinx.coroutines.delay
import kotlin.math.floor

@Composable
fun TimeIndicator(
    modifier: Modifier = Modifier,
    isRunning: Boolean,
    timeConverter: TimeConverter = TimeConverter(),
    duration: Long,
    stroke: Dp = 36.dp,
){

    var progress by remember {
        mutableStateOf(1f)
    }

    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    var currentTime by remember {
        mutableStateOf(duration)
    }

    LaunchedEffect(
        key1 = currentTime,
        key2 = isRunning
    ) {
        if(currentTime > 0 && isRunning) {
            delay(1000L)
            currentTime -= 1000L
            progress = currentTime / duration.toFloat()
        }
    }

    Box(
        modifier = modifier
            .onSizeChanged {
                size = it
            },
        contentAlignment = Alignment.Center
    ){
        Canvas(modifier = modifier){
            drawArc(
                color = Purple80,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(
                    stroke.toPx(),
                    cap = StrokeCap.Butt
                )
            )
            drawArc(
                color = Purple40,
                startAngle = -90f,
                sweepAngle = 360 * progress,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(
                    stroke.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }

        val centerText = buildAnnotatedString {
            withStyle(
                style = SpanStyle(fontSize = 48.sp)
            ){
                append(timeConverter.getLeftMinutes(currentTime).toString())
            }
            withStyle(
                style = SpanStyle(fontSize = 32.sp)
            ){
                append(":")
                append(timeConverter.getLeftSeconds(currentTime).toString())
            }
        }

        Text(
            text = centerText,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }
}