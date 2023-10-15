package com.vicryfahreza.msibmovieapp.screens.upcoming

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vicryfahreza.msibmovieapp.R
import com.vicryfahreza.msibmovieapp.constants.ImageSize
import com.vicryfahreza.msibmovieapp.data_models.Movie
import com.vicryfahreza.msibmovieapp.extensions.getBackdropUrl
import com.vicryfahreza.msibmovieapp.extensions.getGenresText
import com.vicryfahreza.msibmovieapp.extensions.getPosterUrl
import com.vicryfahreza.msibmovieapp.extensions.getReleaseDayMonth

@Composable
fun UpcomingItem(movie: Movie, onClick: (Movie) -> Unit) {
    val releaseDayMonth = remember(movie) { movie.getReleaseDayMonth() }
    val subText = remember(releaseDayMonth) {
        if (releaseDayMonth != null) "Coming on $releaseDayMonth" else null
    }
    val genresText = remember(movie) { movie.getGenresText() }

    Column(Modifier
        .clickable(
            interactionSource = MutableInteractionSource(),
            indication = rememberRipple(),
            onClick = { onClick(movie) })
        .padding(bottom = 20.dp)
    ) {
        Box {
            Column {
                Box() {
                    AsyncImage(
                        model = movie.getBackdropUrl(ImageSize.ORIGINAL),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.8f)
                            .background(colorResource(R.color.dark_gray)),
                        contentScale = ContentScale.Crop,
                    )

                    Box(Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .aspectRatio(5.35f)
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(Color.Transparent, Color.Black)
                            )
                        )
                    )
                }

                Row(Modifier.padding(horizontal = 8.dp)) {
                    Box(Modifier.width(100.dp))
                    Column(Modifier.padding(start = 12.dp, bottom = 8.dp)) {
                        Text(
                            text = movie.title,
                            color = colorResource(R.color.text_primary),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = subText.orEmpty(),
                            modifier = Modifier.padding(top = 4.dp),
                            color = colorResource(R.color.text_secondary),
                            fontSize = 14.sp
                        )
                    }
                }
            }

            AsyncImage(
                model = movie.getPosterUrl(),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 8.dp)
                    .width(100.dp)
                    .aspectRatio(0.66f)
                    .clip(RoundedCornerShape(4.dp))
                    .background(colorResource(R.color.divider)),
                contentScale = ContentScale.Crop,
            )
        }

        Text(
            text = movie.overview,
            modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
            color = colorResource(R.color.text_secondary),
            fontSize = 14.sp,
            lineHeight = 21.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        if (genresText != null) {
            Text(
                text = genresText,
                modifier = Modifier.padding(top = 6.dp, start = 8.dp, end = 8.dp),
                color = colorResource(R.color.text_primary),
                fontSize = 12.sp,
                lineHeight = 18.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}