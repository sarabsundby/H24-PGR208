package com.example.rickandmortyapp.screens.favoritedCharacters

import androidx.lifecycle.ViewModel
import com.example.rickandmortyapp.data.data_classes.APICharacter
import com.example.rickandmortyapp.data.services.APICharacterRepository

class FavoritedCharactersViewModel : ViewModel() {
    val favoritedCharacters = APICharacterRepository.favoritedCharacters

    fun toggleFavorite(character: APICharacter) {
        APICharacterRepository.toggleFavorite(character)
    }
}
