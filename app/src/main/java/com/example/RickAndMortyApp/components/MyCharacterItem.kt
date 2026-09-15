package com.example.rickandmortyapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rickandmortyapp.data.data_classes.MyCharacter

@Composable
fun CharacterItem(
    myCharacter: MyCharacter,
    onDelete: ((Int) -> Unit)? = null,
    onUpdate: (MyCharacter) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(myCharacter.name) }
    var species by remember { mutableStateOf(myCharacter.species) }
    var gender by remember { mutableStateOf(myCharacter.gender) }

    Card(shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp, RoundedCornerShape(12.dp))
    ) {
        if (isEditing) {
            Column(modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Edit character",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = species,
                    onValueChange = { species = it },
                    label = { Text("Species") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = gender,
                    onValueChange = { gender = it },
                    label = { Text("Gender") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { isEditing = false },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                    ) {
                        Text("Cancel")
                    }
                    Button(onClick = {
                            onUpdate(
                                myCharacter.copy(
                                    name = name,
                                    species = species,
                                    gender = gender
                                )
                            )
                            isEditing = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(76, 175, 80))
                    ) {
                        Text("Save")
                    }
                }
            }
        } else {

            Row(modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = myCharacter.image,
                    contentDescription = myCharacter.name,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .padding(end = 16.dp)
                )
                Column {
                    Row {
                        Text(
                            text = myCharacter.name,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF212121)
                        )
                        Text(
                            text = " (ID: ${myCharacter.id})",
                            fontSize = 16.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Species: ${if (myCharacter.species.isBlank()) "Undefined" else myCharacter.species}",
                        fontSize = 16.sp,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Gender: ${if (myCharacter.gender.isBlank()) "Undefined" else myCharacter.gender}",
                        fontSize = 16.sp,
                    )

                    Row {
                        Button(
                            onClick = { isEditing = true },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Blue)
                        ) {
                            Text(
                                text = "Edit",
                                fontSize = 18.sp
                            )
                        }
                        Button(
                            onClick = { onDelete?.invoke(myCharacter.id) },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Red),
                        ) {
                            Text(
                                text = "Delete",
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
