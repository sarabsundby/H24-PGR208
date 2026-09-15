package com.example.rickandmortyapp.screens.homePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.RickAndMortyApp.R


@Composable
fun HomePageScreen(homePageViewModel: HomePageViewModel) {
    val characterList by homePageViewModel.characterList.collectAsState()
    var currentImage by remember { mutableStateOf<String?>(null) }
    var isLocalImageDisplayed by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        homePageViewModel.getAPIIcons()
    }

    if (isLocalImageDisplayed && characterList.isNotEmpty() && currentImage == null) {
        currentImage = characterList.firstOrNull()?.image
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "The Rick and Morty App",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Box(modifier = Modifier.padding(top = 10.dp).background(Color.LightGray, shape = RoundedCornerShape(10.dp))){
            Text(modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Center,
                text =
                "Have fun exploring all the characters in the Rick and Morty universe!\n" +
                        "Favorite the ones you like the best," +
                        " and try to make your own characters.\n" +
                        "The possibilities are endless!"
            )
        }

        if (isLocalImageDisplayed) {
            Image(
                painter = painterResource(id = R.drawable.rickandmorty),
                contentDescription = "AI-generated image of Rick and Morty",
                modifier = Modifier
                    .size(300.dp)
                    .padding(8.dp)
                    .clickable {
                        isLocalImageDisplayed = false
                    }
            )
        } else {
            currentImage?.let { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Character-image.",
                    modifier = Modifier
                        .size(300.dp)
                        .clickable {
                            currentImage = homePageViewModel.getNextCharacterImage()
                        }
                )
            } ?: Text(
                text = "Loading images...",
                fontSize = 16.sp,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "We hope you enjoy!",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Text(
            text = "PS. did you try to click the image?",
            fontSize = 14.sp,
            color = Color.LightGray,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
