package com.bangkit.eventapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bangkit.eventapp.ui.components.BottomBar
import com.bangkit.eventapp.ui.navigation.Screen
import com.bangkit.eventapp.ui.screen.about.AboutScreen
import com.bangkit.eventapp.ui.screen.bookmark.BookmarkScreen
import com.bangkit.eventapp.ui.screen.detail.DetailScreen
import com.bangkit.eventapp.ui.screen.home.HomeScreen
import com.bangkit.eventapp.ui.theme.EventAppTheme

@Composable
fun EventApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailEvent.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { eventId ->
                        navController.navigate(Screen.DetailEvent.createRoute(eventId))
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                route = Screen.DetailEvent.route,
                arguments = listOf(navArgument("eventId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("eventId") ?: -1L
                DetailScreen(
                    eventId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                )
            }
            composable(Screen.Bookmark.route) {
                BookmarkScreen(
                    navigateToDetail = { eventId ->
                        navController.navigate(Screen.DetailEvent.createRoute(eventId))
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    EventAppTheme {
        EventApp()
    }
}