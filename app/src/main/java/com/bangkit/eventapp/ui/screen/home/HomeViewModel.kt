package com.bangkit.eventapp.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.eventapp.data.EventRepository
import com.bangkit.eventapp.model.BookmarkEvent
import com.bangkit.eventapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: EventRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<BookmarkEvent>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<BookmarkEvent>>> get() = _uiState

    init {
        getAllEvent()
    }

    fun getAllEvent() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAllEvents()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { bookmarkEvents ->
                    Log.d("Test", "State updated: ${_uiState.value}")
                    _uiState.value = UiState.Success(bookmarkEvents)
                }
        }
    }

    fun updateBookmarkEvent(eventId: Long, isBookmark: Boolean) {
        viewModelScope.launch {
            repository.updateBookmarkEvent(eventId, isBookmark)
                .collect {
                    // Log the new state
                    Log.d("Test2", "State updated: ${_uiState.value}")
                    getAllEvent()
                }
        }
    }
}