package com.example.rickandmortyapp.data.room

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.rickandmortyapp.data.data_classes.MyCharacter

object MyCharacterRepository {
    private lateinit var _appDatabase: AppDatabase
    private val myCharacterDao by lazy { _appDatabase.myCharacterDao() }

    fun initializeDatabase(context: Context) {
        try {
            _appDatabase = Room.databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = "myCharacter-database"
            ).build()
        }catch (e: Exception){
            Log.e("SQLException", "SQLException ved henting av data: ${e.message}")
        }
    }

    suspend fun getAllMyCharacters(): List<MyCharacter> {
        try {
            return myCharacterDao.getAllMyCharacters()
        }catch (e: Exception){
            Log.e("SQLException", "SQLException ved henting av data: ${e.message}")
            return emptyList()
        }
    }

    suspend fun insertMyCharacter(myCharacter: MyCharacter) : Long {
        try {
            return myCharacterDao.insertMyCharacter(myCharacter)
        } catch (e: Exception) {
            Log.e("SQLException", "SQLException ved henting av data: ${e.message}")
            return -1L
        }
    }

    suspend fun deleteCharacterById(characterId: Int) : Int{
        try {
            return myCharacterDao.deleteMyCharacter(characterId)
        }catch (e: Exception){
            Log.e("SQLException", "SQLException ved henting av data: ${e.message}")
            return 0
        }
    }

    suspend fun characterExists(name: String): Boolean{
        try {
            return myCharacterDao.getCharacterByName(name) != null
        }catch (e: Exception){
            Log.e("SQLException", "SQLException ved henting av data: ${e.message}")
            return false
        }
    }

    suspend fun updateCharacter(myCharacter: MyCharacter): Any {
        try {
            return myCharacterDao.updateMyCharacter(myCharacter)
        } catch (e: Exception) {
            Log.e("SQLException", "SQLException ved henting av data: ${e.message}")
            return 0
        }
    }
}