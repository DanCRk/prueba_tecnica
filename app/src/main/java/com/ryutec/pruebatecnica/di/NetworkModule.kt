package com.ryutec.pruebatecnica.di

import com.ryutec.pruebatecnica.data.network.RickAndMortyAPIClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/character/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOperationsAPIClient(retrofit: Retrofit):RickAndMortyAPIClient{
        return retrofit.create(RickAndMortyAPIClient::class.java)
    }

}