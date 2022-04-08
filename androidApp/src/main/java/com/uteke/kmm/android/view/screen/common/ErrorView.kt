package com.uteke.kmm.android.view.screen.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

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

@Composable
@Preview
fun ErrorViewPreview() {
    ErrorView(message = "error message")
}