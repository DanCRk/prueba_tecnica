package com.ryutec.pruebatecnica.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ryutec.pruebatecnica.data.database.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characters: List<CharacterEntity>)


    @Query("SELECT * FROM character_table WHERE id <= :top AND id >= :bottom")
    suspend fun getPageOfCharacters(top:Int,bottom:Int): List<CharacterEntity>?
}