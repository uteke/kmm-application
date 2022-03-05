package com.uteke.kmm.android.view.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun LoaderView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(progress = 0.5f)
    }
}

@Composable
fun ErrorView(message: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = message,
            fontSize = 12.sp,
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.error
        )
    }
}