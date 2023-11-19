package com.bangkit.eventapp.model

data class Event(
    val id: Long,
    val image: Int,
    val title: String,
    val description: String,
    val location: String,
    val startDate: String,
    val endDate: String,
    val bookmark: Boolean = false,
)
