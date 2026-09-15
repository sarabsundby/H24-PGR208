package com.example.rickandmortyapp.screens.myCharacter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmortyapp.components.CharacterItem


@Composable
fun CharacterListScreen(charactersListViewModel: MyCharacterListViewModel){
    charactersListViewModel.setMyCharacters()
    val myCharacters = charactersListViewModel.myCharacters.collectAsState()
    val amountOfCharacters = myCharacters.value

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            "My characters",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        if(amountOfCharacters.size <= 1) {
            Text(
                text = "You currently have ${amountOfCharacters.size} character saved.",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp,
                color = Color.Gray
            )
        }else {
            Text("You currently have ${amountOfCharacters.size} characters saved.",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp,
                color = Color.Gray
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(myCharacters.value) { character ->
                CharacterItem(
                    myCharacter = character,
                    onDelete = { id -> charactersListViewModel.deleteMyCharacter(id) },
                    onUpdate = { updatedCharacter -> charactersListViewModel.updateMyCharacter(updatedCharacter) }
                )
            }
        }
    }
}
