package com.bangkit.eventapp.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.eventapp.R
import com.bangkit.eventapp.di.Injection
import com.bangkit.eventapp.ui.ViewModelFactory
import com.bangkit.eventapp.ui.common.UiState
import com.bangkit.eventapp.ui.theme.EventAppTheme
import com.bangkit.eventapp.ui.theme.Gray500
import com.bangkit.eventapp.ui.theme.Green500
import com.bangkit.eventapp.ui.theme.Red500
import com.bangkit.eventapp.ui.theme.Shapes
import com.bangkit.eventapp.utils.withDateFormat

@Composable
fun DetailScreen(
    eventId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getEventById(eventId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    image = data.image,
                    title = data.title,
                    description = data.description,
                    location = data.location,
                    startDate = data.startDate,
                    endDate = data.endDate,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    @DrawableRes image: Int,
    title: String,
    description: String,
    location: String,
    startDate: String,
    endDate: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .graphicsLayer {
                        translationY = 0.5f * scrollState.value
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxSize()
                        .scale(1.1f)
                        .blur(radius = 10.dp)
                )
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(210f / 297f)
                        .padding(24.dp)
                        .clip(Shapes.small)
                )
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .shadow(4.dp)
                        .clip(Shapes.small)
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Column(
                        modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = stringResource(id = R.string.start_date),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = location,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Gray500,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                        Row(
                            modifier = modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.line_start_24px),
                                contentDescription = stringResource(id = R.string.start_date),
                                tint = Red500
                            )
                            Text(
                                text = startDate,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Gray500,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                        Row(
                            modifier = modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.line_end_24px),
                                contentDescription = stringResource(id = R.string.end_date),
                                tint = Green500
                            )
                            Text(
                                text = endDate,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Gray500,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                    Divider()
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.back),
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .offset(16.dp, 16.dp)
                .background(MaterialTheme.colorScheme.background, CircleShape)
                .clip(CircleShape)
                .clickable { onBackClick() }
                .padding(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    EventAppTheme {
        DetailContent(
            R.drawable.event_1,
            title = "Stand Up Comedy",
            description = "Join us for a night of laughter and fun!",
            location = "City Park",
            startDate = "2022-06-24".withDateFormat(),
            endDate = "2022-06-24".withDateFormat(),
            onBackClick = {},
        )
    }
}