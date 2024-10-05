package com.ryutec.pruebatecnica.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ryutec.pruebatecnica.data.database.dao.CharacterDao
import com.ryutec.pruebatecnica.data.database.entity.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CharacterDatabase : RoomDatabase(){
    abstract fun getCharacterDao(): CharacterDao
}