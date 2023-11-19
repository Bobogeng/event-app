package com.bangkit.eventapp.data

import com.bangkit.eventapp.model.BookmarkEvent
import com.bangkit.eventapp.model.Event
import com.bangkit.eventapp.model.FakeEventDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class EventRepository {
    private val bookmarkEvents = mutableListOf<BookmarkEvent>()

    init {
        if (bookmarkEvents.isEmpty()) {
            FakeEventDataSource.dummyEvent.forEach {
                bookmarkEvents.add(BookmarkEvent(it, false))
            }
        }
    }

    fun getAllEvents(): Flow<List<BookmarkEvent>> {
        return flowOf(bookmarkEvents)
    }

    fun getEventById(eventId: Long): BookmarkEvent {
        return bookmarkEvents.first {
            it.event.id == eventId
        }
    }

    fun updateBookmarkEvent(eventId: Long, isBookmark: Boolean): Flow<Boolean> {
        val index = bookmarkEvents.indexOfFirst { it.event.id == eventId }
        val result = if (index >= 0) {
            val bookmarkEvent = bookmarkEvents[index]
            bookmarkEvents[index] =
                bookmarkEvent.copy(event = bookmarkEvent.event, bookmark = isBookmark)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    companion object {
        @Volatile
        private var instance: EventRepository? = null

        fun getInstance(): EventRepository =
            instance ?: synchronized(this) {
                EventRepository().apply {
                    instance = this
                }
            }
    }
}