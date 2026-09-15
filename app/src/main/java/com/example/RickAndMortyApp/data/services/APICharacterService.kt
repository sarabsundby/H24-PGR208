package com.example.rickandmortyapp.data.services

import com.example.rickandmortyapp.data.data_classes.APICharacter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APICharacterService {
    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ) : Response<APICharacter>

    @GET("character")
    suspend fun getAPICharacters() : Response<APIResponse>
    data class APIResponse(
        val results: List<APICharacter>
    )
}

