package com.example.lf.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.lf.model.Movie
import com.example.lf.viewmodel.DetailViewModel

/*
UI code for displaying details of a particular Movie
 */
@Composable
fun DetailScreen() {

    val detailViewModel: DetailViewModel = hiltViewModel()
    val state_movie = detailViewModel.state.movie

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        MoviePoster(state_movie)
        Column(
            Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 90.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = state_movie.title,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 38.sp,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center
            )

            MovieAttributes(state_movie, modifier = Modifier)
            TextBlocks(icon = Icons.Filled.Info, heading = "Movie Plot", text = state_movie.plot)
            TextBlocks(
                icon = Icons.Filled.Person,
                heading = "Movie Actors",
                text = state_movie.actors
            )
        }
    }

}

@Composable
fun TextBlocks(icon: ImageVector, heading: String, text: String) {
    Row {
        Icon(imageVector = icon, contentDescription = "", tint = MaterialTheme.colorScheme.primary)
        Text(
            text = heading,
            Modifier.padding(start = 10.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
        )
    }
    Text(text = text, style = MaterialTheme.typography.bodyMedium)
}

@Composable
fun MovieAttributes(value: Movie, modifier: Modifier) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value.imdb_rating,
            modifier.padding(start = 6.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = modifier.width(25.dp))
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value.runtime,
            modifier.padding(start = 6.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = modifier.width(25.dp))
        Icon(
            imageVector = Icons.Filled.LocationOn,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value.country,
            modifier.padding(start = 6.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
fun MoviePoster(value: Movie) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .width(250.dp)
            .padding(top = 40.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.TopCenter
    ) {
        AsyncImage(
            model = value.poster, contentDescription = value.title,
            Modifier
                .width(250.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .width(250.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color(0xB91A1B1B),
                        )
                    ), shape = RoundedCornerShape(16.dp)
                )
        )
    }
}

