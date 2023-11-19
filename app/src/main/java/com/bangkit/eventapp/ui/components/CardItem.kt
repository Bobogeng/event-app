package com.bangkit.eventapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.eventapp.R
import com.bangkit.eventapp.ui.theme.EventAppTheme
import com.bangkit.eventapp.ui.theme.Shapes

@Composable
fun CardItem(
    eventId: Long,
    image: Int,
    title: String,
    description: String,
    isBookmark: Boolean,
    modifier: Modifier = Modifier,
    onBookmarkClick: (eventId: Long, isBookmark: Boolean) -> Unit,
) {
    Column(
        modifier = modifier
            .shadow(4.dp)
            .clip(Shapes.small)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = modifier
                .aspectRatio(210f / 297f)
                .fillMaxWidth()
                .clip(Shapes.large)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Icon(
                imageVector = if (isBookmark) {
                    ImageVector.vectorResource(id = R.drawable.bookmark_filled_24px)
                } else {
                    ImageVector.vectorResource(id = R.drawable.bookmark_24px)
                },
                contentDescription = stringResource(R.string.bookmark),
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset((-8).dp, 8.dp)
                    .background(MaterialTheme.colorScheme.background, CircleShape)
                    .clip(CircleShape)
                    .clickable { onBookmarkClick(eventId, !isBookmark) }
                    .padding(8.dp)
            )
        }
        Column(
            modifier = modifier
                .height(96.dp)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardItemPreview() {
    EventAppTheme {
        CardItem(eventId = 1, image = R.drawable.event_1, title = "Stand Up Comedy", description = "Join us for a night of laughter and fun!", isBookmark = false, onBookmarkClick = { _, _ -> })
    }
}