package com.ekalyoncu.timer.presentation.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ekalyoncu.timer.R
import com.ekalyoncu.timer.presentation.ui.main.components.TimeIndicator
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val duration by remember {
        mutableStateOf(25 * 60000L)
    }

    var isRunning by remember {
        mutableStateOf(true)
    }

    var progress by remember {
        mutableStateOf(1f)
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

    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            TimeIndicator(
                modifier = Modifier
                    .size(200.dp),
                isRunning = isRunning,
                duration = duration,
                currentTime = currentTime,
                progress = progress
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                FilledTonalIconButton(
                    modifier = Modifier
                        .size(48.dp),
                    onClick = {
                        isRunning = !isRunning
                    }
                ){
                    Icon(
                        painter = painterResource(
                            id = if(isRunning) R.drawable.ui_pause else R.drawable.ui_play
                        ),
                        contentDescription = null
                    )
                }
                FilledTonalIconButton(
                    modifier = Modifier
                        .size(48.dp),
                    onClick = {
                        progress = 1f
                        currentTime = 25 * 60000L
                    }
                ){
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ui_stop
                        ),
                        contentDescription = null
                    )
                }
            }

        }
    }
}