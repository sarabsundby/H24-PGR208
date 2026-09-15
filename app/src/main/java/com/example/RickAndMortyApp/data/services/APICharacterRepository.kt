package com.example.rickandmortyapp.data.services

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.rickandmortyapp.data.data_classes.APICharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object APICharacterRepository {
    private val _okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).build()

    private val _retrofit = Retrofit.Builder()
        .client(_okHttpClient)
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(
            GsonConverterFactory.create()
        ).build()

    private val _characterService = _retrofit.create(APICharacterService::class.java)

    suspend fun getCharacterById(id: Int) : APICharacter? {
        try {
            val response = _characterService.getCharacterById(id)

            if(response.isSuccessful){
                return response.body()
            }else{
                return null
            }
        }catch (e: Exception){
            Log.e("SQLException", "SQLException ved henting av data: ${e.message}")
            return null
        }
    }

    suspend fun getAPICharacters(): List<APICharacter> {
        return withContext(Dispatchers.IO) {
            try {
                val response = _characterService.getAPICharacters()
                if (response.isSuccessful) response.body()?.results ?: emptyList()
                else emptyList()
            } catch (e: Exception) {
                Log.e("SQLException", "SQLException ved henting av data: ${e.message}")
                emptyList()
            }
        }
    }

    private val _favoritedCharacters = MutableStateFlow<List<APICharacter>>(emptyList())
    val favoritedCharacters: StateFlow<List<APICharacter>> = _favoritedCharacters

    fun toggleFavorite(character: APICharacter) {
        try {
            val currentFavorites = _favoritedCharacters.value.toMutableList()
            if (currentFavorites.contains(character)) {
                currentFavorites.remove(character)
            } else {
                currentFavorites.add(character)
            }
            _favoritedCharacters.value = currentFavorites
        }catch (e: Exception) {
            Log.e("SQLException", "SQLException ved henting av data: ${e.message}")
        }
    }
}