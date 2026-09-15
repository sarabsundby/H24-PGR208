package com.example.rickandmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.rickandmortyapp.data.room.MyCharacterRepository
import com.example.rickandmortyapp.navigation.AppNavigation
import com.example.rickandmortyapp.screens.addCharacter.AddCharacterListViewModel
import com.example.rickandmortyapp.screens.apiCharacter.APICharacterViewModel
import com.example.rickandmortyapp.screens.myCharacter.MyCharacterListViewModel
import com.example.RickAndMortyApp.ui.theme.AndroidExamTheme
import com.example.rickandmortyapp.screens.homePage.HomePageViewModel
import com.example.rickandmortyapp.screens.favoritedCharacters.FavoritedCharactersViewModel

class MainActivity : ComponentActivity() {
    // Bruker MainActivity til å lage ViewModels for å sende til Screens
    private val _homePageViewModel : HomePageViewModel by viewModels()
    private val _addCharacterListViewModel : AddCharacterListViewModel by viewModels()
    private val _My_characterListViewModel : MyCharacterListViewModel by viewModels()
    private val _apiCharacterViewModel : APICharacterViewModel by viewModels()
    private val _favoritedCharactersViewModel : FavoritedCharactersViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MyCharacterRepository.initializeDatabase((applicationContext))

        enableEdgeToEdge()
        setContent {
            AndroidExamTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        AppNavigation(
                            _homePageViewModel,
                            _addCharacterListViewModel,
                            _My_characterListViewModel,
                            _apiCharacterViewModel,
                            _favoritedCharactersViewModel
                        )
                    }
                }
            }
        }
    }
}