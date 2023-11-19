package com.bangkit.eventapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Bookmark : Screen("bookmark")
    object About : Screen("about")
    object DetailEvent : Screen("home/{eventId}") {
        fun createRoute(eventId: Long) = "home/$eventId"
    }
}
