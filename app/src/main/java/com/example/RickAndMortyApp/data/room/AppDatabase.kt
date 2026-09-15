package com.example.rickandmortyapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmortyapp.data.data_classes.MyCharacter


@Database(
    entities = [MyCharacter::class],
    version = 5,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase(){
    abstract fun myCharacterDao() : MyCharacterDao
}