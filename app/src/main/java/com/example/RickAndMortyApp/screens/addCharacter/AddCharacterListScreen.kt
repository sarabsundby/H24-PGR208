package com.example.rickandmortyapp.screens.addCharacter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.RickAndMortyApp.R
import com.example.rickandmortyapp.data.data_classes.MyCharacter

@Composable
fun AddCharacterListScreen(addCharacter: AddCharacterListViewModel) {
    addCharacter.setMyCharacters()

    var _newCharacterName by remember { mutableStateOf("") }
    var _newCharacterSpecies by remember { mutableStateOf("") }
    var _newCharacterGender by remember { mutableStateOf("") }
    var _newCharacterImage by remember { mutableStateOf(R.drawable.baseline_person_24) }
    var outputMessage by remember { mutableStateOf("") }
    var messageColor by remember { mutableStateOf(Color(0, 0, 0)) }
    var isMenuClicked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Create your own character",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            "Add a character of your choice to the Rick and Morty universe!" +
                    " Just fill out the fields below and press save.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = Color(20, 20, 20)
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = _newCharacterName,
            onValueChange = { _newCharacterName = it },
            label = { Text("Name") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_drive_file_rename_outline_24),
                    contentDescription = "Person. Icon"
                )
            }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = _newCharacterSpecies,
            onValueChange = { _newCharacterSpecies = it },
            label = { Text("Species") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_android_24),
                    contentDescription = "Android. Icon"
                )
            }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = _newCharacterGender,
            onValueChange = { _newCharacterGender = it },
            label = { Text("Gender") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_transgender_24),
                    contentDescription = "Gender. Icon"
                )
            }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = _newCharacterImage),
                contentDescription = "Selected Icon",
                modifier = Modifier
                    .size(60.dp)
            )
            IconButton(onClick = { isMenuClicked = true }) {
                Icon(
                    painter = painterResource(R.drawable.dropdown_arrow),
                    contentDescription = "Selected Icon",
                    modifier = Modifier.size(40.dp)
                )
            }
            DropdownMenu(expanded = isMenuClicked, onDismissRequest = { isMenuClicked = false }
            ) {
                val icons = listOf(
                    R.drawable.baseline_person_24,
                    R.drawable.face_icon_1,
                    R.drawable.face_icon_2,
                    R.drawable.face_icon_3,
                    R.drawable.face_icon_4,
                    R.drawable.face_icon_5,
                    R.drawable.face_icon_6
                )
                icons.forEach { icon ->
                    DropdownMenuItem(
                        text = { Text("") },
                        onClick = {
                            _newCharacterImage = icon
                            isMenuClicked = false
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = "Icon $icon",
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    )
                }
            }
        }
        Button(
            onClick = {
                if (_newCharacterName.isBlank()) {
                    outputMessage =
                        "Failed to add character. Please enter a name for the character to continue."
                    messageColor = Color(244, 67, 54)
                } else {
                    addCharacter.characterExists(_newCharacterName) { exists ->
                        if (exists) {
                            outputMessage = "A character with this name already exists!"
                            messageColor = Color(244, 67, 54)
                        } else {
                            addCharacter.insertMyCharacter(
                                MyCharacter(
                                    name = _newCharacterName,
                                    species = _newCharacterSpecies,
                                    gender = _newCharacterGender,
                                    image = _newCharacterImage
                                )
                            )
                            _newCharacterName = ""
                            _newCharacterSpecies = ""
                            _newCharacterGender = ""
                            _newCharacterImage = R.drawable.baseline_person_24
                            outputMessage = "Character successfully added!"
                            messageColor = Color(76, 175, 80)
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(76, 175, 80)),
            elevation = ButtonDefaults.elevatedButtonElevation(12.dp)
        ) {
            Text(
                text = "Save character",
                fontSize = 16.sp,
                color = Color.Black
            )
        }

        if (outputMessage.isNotEmpty()) {
            Text(
                text = outputMessage,
                modifier = Modifier.padding(top = 8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = messageColor
            )
        }
    }
}
