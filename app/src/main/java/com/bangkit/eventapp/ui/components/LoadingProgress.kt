package com.bangkit.eventapp.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.eventapp.ui.theme.EventAppTheme

@Composable
fun LoadingProgress(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.width(32.dp),
    )
}

@Preview
@Composable
fun LoadingProgressPreview() {
    EventAppTheme {
        LoadingProgress()
    }
}