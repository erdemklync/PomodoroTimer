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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    var isRunning by remember {
        mutableStateOf(true)
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
                duration = 150000
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
                        isRunning = !isRunning
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