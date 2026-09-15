package com.example.rickandmortyapp.screens.addCharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.data_classes.MyCharacter
import com.example.rickandmortyapp.data.room.MyCharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AddCharacterListViewModel : ViewModel() {
    private val _characters = MutableStateFlow<List<MyCharacter>>(emptyList())

    fun setMyCharacters(){
        viewModelScope.launch(Dispatchers.IO) {
            _characters.value = MyCharacterRepository.getAllMyCharacters()
        }
    }

    fun insertMyCharacter(myCharacter: MyCharacter) {
        viewModelScope.launch(Dispatchers.IO) {
            MyCharacterRepository.insertMyCharacter(myCharacter)
            _characters.value = listOf(myCharacter) + _characters.value
        }
    }

    fun characterExists(name: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val exists = MyCharacterRepository.characterExists(name)
            onResult(exists)
        }
    }
}