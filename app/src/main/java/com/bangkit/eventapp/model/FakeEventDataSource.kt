package com.bangkit.eventapp.model

import com.bangkit.eventapp.R
import com.bangkit.eventapp.utils.withDateFormat

object FakeEventDataSource {
    val dummyEvent = listOf(
        Event(
            id = 1,
            image = R.drawable.event_1,
            title = "Stand Up Comedy",
            description = "Join us for a night of laughter and fun!",
            location = "City Park",
            startDate = "2022-06-24".withDateFormat(),
            endDate = "2022-06-24".withDateFormat()
        ),
        Event(
            id = 2,
            image = R.drawable.event_2,
            title = "First Person Shooter Tournament",
            description = "Compete in the ultimate FPS tournament and prove your skills!",
            location = "Community Center",
            startDate = "2022-10-15".withDateFormat(),
            endDate = "2022-10-15".withDateFormat()
        ),
        Event(
            id = 3,
            image = R.drawable.event_3,
            title = "Talent Show",
            description = "Showcase your talent in our exciting talent show!",
            location = "Sports Arena",
            startDate = "2022-06-14".withDateFormat(),
            endDate = "2022-06-14".withDateFormat()
        ),
        Event(
            id = 4,
            image = R.drawable.event_4,
            title = "Movie Night",
            description = "Enjoy a cozy movie night with your friends and family!",
            location = "Cinema Hall",
            startDate = "2022-11-20".withDateFormat(),
            endDate = "2022-11-20".withDateFormat()
        ),
        Event(
            id = 5,
            image = R.drawable.event_5,
            title = "Kids Winter Camp",
            description = "An adventurous winter camp for kids filled with games and activities!",
            location = "Winter Wonderland Campsite",
            startDate = "2022-11-20".withDateFormat(),
            endDate = "2022-11-24".withDateFormat()
        ),
        Event(
            id = 6,
            image = R.drawable.event_6,
            title = "Comedy Night",
            description = "Get ready for a night full of laughs and entertainment!",
            location = "Comedy Club",
            startDate = "2022-09-24".withDateFormat(),
            endDate = "2022-09-24".withDateFormat()
        ),
        Event(
            id = 7,
            image = R.drawable.event_7,
            title = "Outdoor Adventure",
            description = "Embark on an outdoor adventure with fellow nature enthusiasts!",
            location = "Nature Reserve",
            startDate = "2022-11-23".withDateFormat(),
            endDate = "2022-11-23".withDateFormat()
        ),
        Event(
            id = 8,
            image = R.drawable.event_8,
            title = "Game Football",
            description = "Experience the thrill of a football game with friends!",
            location = "Football Stadium",
            startDate = "2022-01-25".withDateFormat(),
            endDate = "2022-01-25".withDateFormat()
        ),
        Event(
            id = 9,
            image = R.drawable.event_9,
            title = "Stand-up Comedy",
            description = "A hilarious stand-up comedy night you won't want to miss!",
            location = "Performing Arts Center",
            startDate = "2022-05-31".withDateFormat(),
            endDate = "2022-05-31".withDateFormat()
        ),
        Event(
            id = 10,
            image = R.drawable.event_10,
            title = "Movie Night",
            description = "Another fantastic movie night with the latest blockbusters!",
            location = "Home Theater",
            startDate = "2022-12-26".withDateFormat(),
            endDate = "2022-12-26".withDateFormat()
        ),
    )
}