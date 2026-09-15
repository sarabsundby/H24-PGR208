package com.example.rickandmortyapp.data.data_classes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val species: String,
    val gender: String,
    val image: Int
)