package com.example.tripgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tripgallery.ui.theme.TripGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TripGalleryTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    TripGalleryApp()
                }
            }
        }
    }
}

@Composable
fun TripGalleryApp() {
    val artworks = listOf(
        Artwork(R.drawable.image1, "New York City", "Statue of Liberty", 2019),
        Artwork(R.drawable.image2, "Washington DC", "Capitol Building", 2024),
        Artwork(R.drawable.image3, "Oregon", "Canon Beach", 2023),
        Artwork(R.drawable.image4, "South Dakota", "Mount Rushmore", 2018),
        Artwork(R.drawable.image5, "Hawaii", "Under the Sea", 2020),
        Artwork(R.drawable.image6, "Nevada", "Las Vegas", 2022),
        Artwork(R.drawable.image7, "California", "Golden Gate Bridge", 2019),
        Artwork(R.drawable.image8, "Wyoming", "Jackson Hole", 2020),
        Artwork(R.drawable.image9, "California", "Walk of Fame", 2021),
        Artwork(R.drawable.image10, "Washington", "Palouse falls", 2022)
    )

    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5F5DC)) // Beige background
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "My Trip Gallery",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        ArtworkDisplay(artwork = artworks[currentIndex])
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                currentIndex = if (currentIndex > 0) currentIndex - 1 else artworks.size - 1
            }) {
                Text(text = "Previous")
            }
            Button(onClick = {
                currentIndex = if (currentIndex < artworks.size - 1) currentIndex + 1 else 0
            }) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun ArtworkDisplay(artwork: Artwork) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = artwork.imageResId),
            contentDescription = null,
            modifier = Modifier.size(300.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = artwork.title,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "by ${artwork.artist} (${artwork.year})",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
