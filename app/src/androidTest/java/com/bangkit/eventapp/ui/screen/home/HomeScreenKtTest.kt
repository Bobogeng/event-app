package com.bangkit.eventapp.ui.screen.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.bangkit.eventapp.EventApp
import com.bangkit.eventapp.R
import com.bangkit.eventapp.model.Event
import com.bangkit.eventapp.ui.theme.EventAppTheme
import com.bangkit.eventapp.utils.withDateFormat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeEvent = Event(
        id = 2,
        image = R.drawable.event_2,
        title = "First Person Shooter Tournament",
        description = "Compete in the ultimate FPS tournament and prove your skills!",
        location = "Community Center",
        startDate = "2022-10-15".withDateFormat(),
        endDate = "2022-10-15".withDateFormat()
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            EventAppTheme {
                EventApp()
            }
        }
    }

    @Test
    fun calculate_area_of_rectangle_correct() {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.placeholder_search)).performTextInput(fakeEvent.title)
    }
}