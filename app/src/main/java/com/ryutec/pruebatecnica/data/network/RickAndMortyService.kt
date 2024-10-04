package com.ryutec.pruebatecnica.data.network

import com.ryutec.pruebatecnica.data.model.rickandmorty.RickAndMortyModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RickAndMortyService @Inject constructor(private val api: RickAndMortyAPIClient){

    suspend fun getCharacters(page:String):RickAndMortyModel{
        return withContext(Dispatchers.IO){
            val response = api.getAllOperations(page)
            response.body() ?: RickAndMortyModel(null, emptyList())
        }
    }
}