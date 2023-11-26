package com.bangkit.eventapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.bangkit.eventapp.R
import com.bangkit.eventapp.ui.theme.EventAppTheme

@Composable
fun NoDataText() {
    Text(
        text = stringResource(R.string.no_data),
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun NoDataTextPreview() {
    EventAppTheme {
        NoDataText()
    }
}