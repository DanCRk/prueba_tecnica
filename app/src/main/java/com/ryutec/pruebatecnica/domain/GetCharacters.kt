package com.ryutec.pruebatecnica.domain

import com.ryutec.pruebatecnica.data.Repository
import com.ryutec.pruebatecnica.data.Resource
import com.ryutec.pruebatecnica.domain.model.Character
import com.ryutec.pruebatecnica.domain.model.toDomain
import javax.inject.Inject

class GetCharacters @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(page: String): Resource<List<Character>> {
        return try {
            val response = repository.getPageOfCharactersFromDataBase(page)
            if (response.isEmpty()) {
                val responseAPI = repository.getPageOfCharactersFromApi(page)
                return Resource.Success(responseAPI.map { it.toDomain() })
            }

            Resource.Success(response.map { it.toDomain() })

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

}