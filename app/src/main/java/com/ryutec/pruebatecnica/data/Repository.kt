package com.ryutec.pruebatecnica.data

import com.ryutec.pruebatecnica.data.model.operations.OperationsModel
import com.ryutec.pruebatecnica.data.model.operations.OperationsProvider
import com.ryutec.pruebatecnica.data.model.rickandmorty.Result
import com.ryutec.pruebatecnica.data.model.rickandmorty.RickAndMortyModel
import com.ryutec.pruebatecnica.data.network.RickAndMortyService
import com.ryutec.pruebatecnica.domain.model.Character
import com.ryutec.pruebatecnica.domain.model.toDomain
import javax.inject.Inject

class Repository @Inject constructor(private val operationsProvider: OperationsProvider, private val api:RickAndMortyService) {

    fun getAllOperations() :Resource<List<OperationsModel>>{
        return try {
            Resource.Success(operationsProvider.operations)
        }catch (e:Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }
    }


    suspend fun getPageOfCharacters(page:String):Resource<List<Character>>{
        return try {
            val response  = api.getCharacters(page).results

            Resource.Success(response.map { it.toDomain() })
        }catch (e:Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

}