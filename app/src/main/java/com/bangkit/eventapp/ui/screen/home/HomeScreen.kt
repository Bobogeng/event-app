package com.bangkit.eventapp.ui.screen.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.eventapp.di.Injection
import com.bangkit.eventapp.model.BookmarkEvent
import com.bangkit.eventapp.ui.ViewModelFactory
import com.bangkit.eventapp.ui.common.UiState
import com.bangkit.eventapp.ui.components.CardItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                Log.d("Test", "HomeScreen")
                HomeContent(
                    events = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    onBookmarkClick = { eventId, isBookmark ->
                        viewModel.updateBookmarkEvent(eventId, isBookmark)
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    events: List<BookmarkEvent>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    onBookmarkClick: (eventId: Long, isBookmark: Boolean) -> Unit,
) {
    Log.d("Test", "HomeContent")
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.testTag("EventList")
    ) {
        items(events, key = { it.event.id }) { data ->
            val event = data.event
            CardItem(
                eventId = event.id,
                image = event.image,
                title = event.title,
                description = event.description,
                isBookmark = data.bookmark,
                modifier = modifier.clickable {
                    navigateToDetail(event.id)
                },
                onBookmarkClick = onBookmarkClick
            )
        }
    }
}