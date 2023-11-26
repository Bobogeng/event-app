package com.bangkit.eventapp.ui.screen.bookmark

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.eventapp.data.EventRepository
import com.bangkit.eventapp.model.Event
import com.bangkit.eventapp.ui.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BookmarkViewModel(private val repository: EventRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Event>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Event>>> get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllBookmarkEvents() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAllBookmarkEvents()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { bookmarkEvents ->
                    Log.d("Test", bookmarkEvents.toString())
                    viewModelScope.launch(Dispatchers.Main) {
                        _uiState.value = UiState.Success(bookmarkEvents)
                    }
                }
        }
    }

    fun updateEvent(eventId: Long, isBookmark: Boolean) {
        viewModelScope.launch {
            repository.updateEvent(eventId, isBookmark)
                .collect {
                    getAllBookmarkEvents()
                }
        }
    }

    fun searchBookmarkEvents(query: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.searchEvents(query)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { events ->
                    viewModelScope.launch(Dispatchers.Main) {
                        _query.value = query
                        _uiState.value = UiState.Success(events)
                    }
                }
        }
    }
}