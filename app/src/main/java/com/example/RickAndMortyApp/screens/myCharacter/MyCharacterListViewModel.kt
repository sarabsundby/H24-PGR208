package com.example.rickandmortyapp.screens.myCharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.data_classes.MyCharacter
import com.example.rickandmortyapp.data.room.MyCharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyCharacterListViewModel : ViewModel(){
    private val _characters = MutableStateFlow<List<MyCharacter>>(emptyList())
    val myCharacters = _characters.asStateFlow()

    fun setMyCharacters(){
        viewModelScope.launch(Dispatchers.IO){
            _characters.value = MyCharacterRepository.getAllMyCharacters()
        }
    }

    fun deleteMyCharacter(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val characterDeleted = MyCharacterRepository.deleteCharacterById(characterId)
            if (characterDeleted > 0) {
                _characters.value = _characters.value.filter { it.id != characterId }
            }
        }
    }

    fun updateMyCharacter(updatedMyCharacter: MyCharacter){
        viewModelScope.launch(Dispatchers.IO){
            MyCharacterRepository.updateCharacter(updatedMyCharacter)
            setMyCharacters()
        }
    }
}