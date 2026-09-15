package com.example.rickandmortyapp.screens.homePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.data_classes.APICharacter
import com.example.rickandmortyapp.data.services.APICharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomePageViewModel : ViewModel() {
    private val _characterList = MutableStateFlow<List<APICharacter>>(emptyList())
    val characterList = _characterList

    private var currentImageIndex = 0

    fun getAPIIcons() {
        viewModelScope.launch {
            _characterList.value = APICharacterRepository.getAPICharacters()
        }
    }

    fun getNextCharacterImage(): String? {
        val characters = _characterList.value
        if (characters.isNotEmpty()) {
            val nextImage = characters[currentImageIndex].image
            currentImageIndex = (currentImageIndex + 1) % characters.size
            return nextImage
        }
        return null
    }
}
