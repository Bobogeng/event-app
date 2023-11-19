package com.bangkit.eventapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.eventapp.data.EventRepository
import com.bangkit.eventapp.model.BookmarkEvent
import com.bangkit.eventapp.model.Event
import com.bangkit.eventapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: EventRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<BookmarkEvent>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<BookmarkEvent>> get() = _uiState

    fun getEventById(eventId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getEventById(eventId))
        }
    }
}