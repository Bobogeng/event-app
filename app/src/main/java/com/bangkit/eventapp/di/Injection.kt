package com.bangkit.eventapp.di

import com.bangkit.eventapp.data.EventRepository

object Injection {
    fun provideRepository(): EventRepository {
        return EventRepository.getInstance()
    }
}