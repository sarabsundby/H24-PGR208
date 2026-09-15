package com.example.rickandmortyapp.screens.apiCharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.data_classes.APICharacter
import com.example.rickandmortyapp.data.services.APICharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class APICharacterViewModel : ViewModel() {
    private val _characterList = MutableStateFlow<List<APICharacter>>(emptyList())
    val characterList = _characterList.asStateFlow()
    private val _character = MutableStateFlow<APICharacter?>(null)
    val searchedCharacter = _character.asStateFlow()
    val favoritedCharacters = APICharacterRepository.favoritedCharacters

    fun setSearchedCharacter(id: Int) {
        viewModelScope.launch {
            _character.value = APICharacterRepository.getCharacterById(id)
        }
    }

    fun getAPICharacters() {
        viewModelScope.launch {
            _characterList.value = APICharacterRepository.getAPICharacters()
        }
    }

    fun toggleFavorite(character: APICharacter) {
        APICharacterRepository.toggleFavorite(character)
    }
}
