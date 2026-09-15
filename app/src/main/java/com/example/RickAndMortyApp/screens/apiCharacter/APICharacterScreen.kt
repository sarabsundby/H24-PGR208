package com.example.rickandmortyapp.screens.apiCharacter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmortyapp.components.APICharacterItem

@Composable
fun APICharacterScreen(apiCharacterViewModel: APICharacterViewModel) {
    val favoritedCharacters by apiCharacterViewModel.favoritedCharacters.collectAsState()
    val searchedCharacter = apiCharacterViewModel.searchedCharacter.collectAsState()
    val characterList by apiCharacterViewModel.characterList.collectAsState()
    var searchedClicked by remember { mutableStateOf(false) }
    var id by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        apiCharacterViewModel.getAPICharacters()
    }

    Column(modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Search for any of the 826 Rick and Morty characters",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text(text = "Enter an ID...") },
            trailingIcon = {
                IconButton(onClick = {
                    val characterId = id.toIntOrNull()
                    if (characterId != null) {
                        apiCharacterViewModel.setSearchedCharacter(characterId)
                        searchedClicked = true
                    } else {
                        apiCharacterViewModel.getAPICharacters()
                        searchedClicked = false
                    }
                }) {
                    Icon(Icons.Filled.Search, contentDescription = "Search")
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        if (searchedClicked) {
            searchedCharacter.value?.let { character ->
                val isFavorite = favoritedCharacters.contains(character)
                APICharacterItem(character, isFavorite, apiCharacterViewModel::toggleFavorite)
            } ?: run {
                Text(
                    text = "Loading characters....",
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        } else {
            LazyColumn(modifier = Modifier
                .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                items(characterList) { character ->
                    val isFavorite = favoritedCharacters.contains(character)
                    APICharacterItem(character, isFavorite, apiCharacterViewModel::toggleFavorite)
                }
            }
        }
    }
}
