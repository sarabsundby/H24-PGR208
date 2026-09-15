package com.example.rickandmortyapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.rickandmortyapp.data.data_classes.MyCharacter

@Dao
interface MyCharacterDao {
    @Query("SELECT * FROM MyCharacter")
    suspend fun getAllMyCharacters() : List<MyCharacter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyCharacter(myCharacter: MyCharacter) : Long

    @Query("SELECT * FROM MyCharacter WHERE id = :id")
    suspend fun getMyCharacterById(id: Int) : MyCharacter?

    @Query("SELECT * FROM MyCharacter WHERE name = :name LIMIT 1")
    suspend fun getCharacterByName(name: String): MyCharacter?

    @Update
    suspend fun updateMyCharacter(myCharacter: MyCharacter)

    @Query("DELETE FROM MyCharacter WHERE id = :characterId")
    suspend fun deleteMyCharacter(characterId: Int): Int
}