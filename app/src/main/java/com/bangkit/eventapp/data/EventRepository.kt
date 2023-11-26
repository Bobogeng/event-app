package com.bangkit.eventapp.data

import com.bangkit.eventapp.model.Event
import com.bangkit.eventapp.model.FakeEventDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class EventRepository {
    private val events = mutableListOf<Event>()

    init {
        if (events.isEmpty()) {
            FakeEventDataSource.dummyEvent.forEach {
                events.add(it)
            }
        }
    }

    fun getAllEvents(): Flow<List<Event>> {
        return flowOf(events)
    }

    fun getEventById(eventId: Long): Event {
        return events.first {
            it.id == eventId
        }
    }

    fun updateEvent(eventId: Long, isBookmark: Boolean): Flow<Boolean> {
        val index = events.indexOfFirst { it.id == eventId }
        val result = if (index >= 0) {
            val event = events[index]
            events[index] = event.copy(bookmark = isBookmark)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAllBookmarkEvents(): Flow<List<Event>> {
        return getAllEvents()
            .map { events ->
                events.filter { event ->
                    event.bookmark
                }
            }
    }

    fun searchEvents(query: String): Flow<List<Event>> {
        return flowOf(
            events.filter {
                it.title.contains(query, ignoreCase = true)
            }
        )
    }

    fun searchBookmarkEvents(query: String): Flow<List<Event>> {
        return flowOf(
            events.filter {
                it.title.contains(query, ignoreCase = true) && it.bookmark
            }
        )
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