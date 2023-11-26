package com.bangkit.eventapp.ui.screen.bookmark

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.eventapp.di.Injection
import com.bangkit.eventapp.model.Event
import com.bangkit.eventapp.ui.ViewModelFactory
import com.bangkit.eventapp.ui.common.UiState
import com.bangkit.eventapp.ui.components.CardItem
import com.bangkit.eventapp.ui.components.ErrorText
import com.bangkit.eventapp.ui.components.LoadingProgress
import com.bangkit.eventapp.ui.components.NoDataText
import com.bangkit.eventapp.ui.components.SearchBar

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit
) {
    val query by viewModel.query

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SearchBar(
            query = query,
            onQueryChange = { query ->
                viewModel.searchBookmarkEvents(query)
            }
        )
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LoadingProgress()
                    }
                    viewModel.getAllBookmarkEvents()
                }
                is UiState.Success -> {
                    if (uiState.data.isEmpty()) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            NoDataText()
                        }
                    }
                    BookmarkContent(
                        events = uiState.data,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail,
                        onBookmarkClick = { eventId, isBookmark ->
                            viewModel.updateEvent(eventId, isBookmark)
                        }
                    )
                }
                is UiState.Error -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        ErrorText()
                    }
                }
            }
        }
    }
}

@Composable
fun BookmarkContent(
    events: List<Event>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    onBookmarkClick: (Long, Boolean) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.testTag("EventList")
    ) {
        items(events, key = { it.id }) { event ->
            CardItem(
                eventId = event.id,
                image = event.image,
                title = event.title,
                description = event.description,
                isBookmark = event.bookmark,
                modifier = modifier.clickable {
                    navigateToDetail(event.id)
                },
                onBookmarkClick = onBookmarkClick
            )
        }
    }
}