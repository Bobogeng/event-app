package com.bangkit.eventapp.ui.screen.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.eventapp.data.EventRepository
import com.bangkit.eventapp.model.BookmarkEvent
import com.bangkit.eventapp.model.Event
import kotlinx.coroutines.launch

class BookmarkViewModel(private val repository: EventRepository) : ViewModel() {
    fun updateBookmarkEvent(eventId: Long, isBookmark: Boolean) {
        viewModelScope.launch {
            repository.updateBookmarkEvent(eventId, isBookmark)
        }
    }
}