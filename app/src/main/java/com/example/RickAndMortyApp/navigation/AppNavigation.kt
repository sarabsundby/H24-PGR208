package com.example.rickandmortyapp.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyapp.screens.homePage.HomePageScreen
import com.example.rickandmortyapp.screens.homePage.HomePageViewModel
import com.example.rickandmortyapp.screens.addCharacter.AddCharacterListScreen
import com.example.rickandmortyapp.screens.addCharacter.AddCharacterListViewModel
import com.example.rickandmortyapp.screens.apiCharacter.APICharacterViewModel
import com.example.rickandmortyapp.screens.apiCharacter.APICharacterScreen
import com.example.rickandmortyapp.screens.favoritedCharacters.FavoritedCharactersViewModel
import com.example.rickandmortyapp.screens.favoritedCharacters.IconShopScreen
import com.example.rickandmortyapp.screens.myCharacter.MyCharacterListViewModel
import com.example.rickandmortyapp.screens.myCharacter.CharacterListScreen
import kotlinx.serialization.Serializable

@Serializable
object Routes {
    const val Home = "home"
    const val Search = "search"
    const val MyCharacters = "show_characters"
    const val AddCharacter = "add_character"
    const val Favorites = "favorites"
}

@Composable
fun AppNavigation(
    _homePageViewModel: HomePageViewModel,
    _addCharacterListViewModel: AddCharacterListViewModel,
    _My_characterListViewModel: MyCharacterListViewModel,
    _apiCharacterViewModel: APICharacterViewModel,
    _favoritedCharactersViewModel: FavoritedCharactersViewModel
) {
    val navController = rememberNavController()
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val navbarTheme = NavigationBarItemDefaults.colors(
        indicatorColor = Color.LightGray,
        selectedIconColor = Color(50, 50, 50),
        selectedTextColor = Color(50, 50, 50),
        unselectedIconColor = Color(105,105,105),
        unselectedTextColor = Color(105,105,105)
    )

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
            NavigationBar(containerColor = Color.Transparent
            ) {

                NavigationBarItem(
                    selected = selectedItemIndex == 1,
                    onClick = { selectedItemIndex = 1
                        navController.navigate(Routes.Search)},
                    icon = {
                        if( selectedItemIndex == 1 ){
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = null)
                        }else{
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = null)
                        }
                    }, label = {
                        Text("Search")
                    }, colors = navbarTheme
                )

                NavigationBarItem(
                    selected = selectedItemIndex == 2,
                    onClick = { selectedItemIndex = 2
                        navController.navigate(Routes.Favorites)
                    },
                    icon = {
                        if( selectedItemIndex == 2 ){
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = null)
                        }else{
                            Icon(
                                imageVector = Icons.Outlined.FavoriteBorder,
                                contentDescription = null)
                        }
                    }, label = {
                        Text("Favorites")
                    }, colors = navbarTheme
                )

                NavigationBarItem(
                    selected = selectedItemIndex == 0,
                    onClick = { selectedItemIndex = 0
                        navController.navigate(Routes.Home)
                    },
                    icon = {
                        if( selectedItemIndex == 0 ){
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = null)
                        }else{
                            Icon(
                                imageVector = Icons.Outlined.Home,
                                contentDescription = null)
                        }
                    }, label = {
                        Text("Home")
                    }, colors = navbarTheme
                )

                NavigationBarItem(
                    selected = selectedItemIndex == 3,
                    onClick = { selectedItemIndex = 3
                        navController.navigate(Routes.MyCharacters)
                    },
                    icon = {
                        if(selectedItemIndex == 3){
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = null
                            )
                        }else{
                            Icon(
                                imageVector = Icons.Outlined.AccountCircle,
                                contentDescription = null)
                        }

                    }, label = {
                        Text(text = "Characters")
                    }, colors = navbarTheme
                )

                NavigationBarItem(
                    selected = selectedItemIndex == 4,
                    onClick = { selectedItemIndex = 4
                        navController.navigate(Routes.AddCharacter)
                    },
                    icon = {
                        if(selectedItemIndex == 4) {
                            Icon(
                                imageVector = Icons.Filled.Add, contentDescription = null
                            )
                        }else{
                            Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
                        }
                    }, label = {
                        Text("Add")
                    }, colors = navbarTheme
                )
            }
        }
    ) { innerPadding ->

        Column( modifier = Modifier.padding(innerPadding) ){
            Column (modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ){
                NavHost(navController = navController, startDestination = Routes.Home
                ) {
                    composable(Routes.Search) {
                        APICharacterScreen(_apiCharacterViewModel)
                    }
                    composable(Routes.MyCharacters) {
                        CharacterListScreen(_My_characterListViewModel)
                    }
                    composable(Routes.Home) {
                        HomePageScreen(_homePageViewModel)
                    }
                    composable(Routes.Favorites) {
                        IconShopScreen(_favoritedCharactersViewModel)
                    }
                    composable(Routes.AddCharacter){
                        AddCharacterListScreen(_addCharacterListViewModel)
                    }
                }
            }
        }
    }
}