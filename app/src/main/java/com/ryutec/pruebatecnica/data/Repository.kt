package com.ryutec.pruebatecnica.data

import com.ryutec.pruebatecnica.data.database.dao.CharacterDao
import com.ryutec.pruebatecnica.data.database.entity.CharacterEntity
import com.ryutec.pruebatecnica.data.database.entity.toDatabase
import com.ryutec.pruebatecnica.data.model.operations.OperationsModel
import com.ryutec.pruebatecnica.data.model.operations.OperationsProvider
import com.ryutec.pruebatecnica.data.model.rickandmorty.Result
import com.ryutec.pruebatecnica.data.model.rickandmorty.RickAndMortyModel
import com.ryutec.pruebatecnica.data.network.RickAndMortyService
import com.ryutec.pruebatecnica.domain.model.Character
import com.ryutec.pruebatecnica.domain.model.toDomain
import javax.inject.Inject

class Repository @Inject constructor(
    private val operationsProvider: OperationsProvider,
    private val api: RickAndMortyService,
    private val dao: CharacterDao
) {

    fun getAllOperations(): Resource<List<OperationsModel>> {
        return try {
            Resource.Success(operationsProvider.operations)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    suspend fun getPageOfCharactersFromApi(page: String): List<Result> {
        val response = api.getCharacters(page).results
        dao.saveCharacters(response.map { it.toDatabase() })
        return response
    }

    suspend fun getPageOfCharactersFromDataBase(page: String) :List<CharacterEntity>{
        val top = (page.toInt() * 20)
        val bottom = top - 19
        return dao.getPageOfCharacters(top, bottom) ?: emptyList()
    }

}