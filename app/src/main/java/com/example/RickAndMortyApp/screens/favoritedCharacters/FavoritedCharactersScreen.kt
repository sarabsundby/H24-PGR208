package com.example.rickandmortyapp.screens.favoritedCharacters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmortyapp.components.APICharacterItem

@Composable
fun IconShopScreen(favoritedCharactersViewModel: FavoritedCharactersViewModel) {
    val favoritedCharacters by favoritedCharactersViewModel.favoritedCharacters.collectAsState()

    Column(modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Favorite characters",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        if (favoritedCharacters.isEmpty()) {
            Text(
                text = "No favorite characters yet. Go to search and click the heart on any of the characters to add them to your favorites.",
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favoritedCharacters) { character ->
                    APICharacterItem(character, true, favoritedCharactersViewModel::toggleFavorite)
                }
            }
        }
    }
}
