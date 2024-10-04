package com.ryutec.pruebatecnica.data.network

import com.ryutec.pruebatecnica.data.model.rickandmorty.RickAndMortyModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RickAndMortyAPIClient {

    @GET(".")
    suspend fun getAllOperations(@Query("page") pageNumber: String): Response<RickAndMortyModel>
}