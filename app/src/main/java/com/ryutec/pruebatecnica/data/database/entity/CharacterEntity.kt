package com.ryutec.pruebatecnica.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ryutec.pruebatecnica.data.model.rickandmorty.Result

@Entity(tableName = "character_table")
data class CharacterEntity (
    @PrimaryKey
    @ColumnInfo(name = "id") var id : Int,
    @ColumnInfo(name = "name") var name : String,
    @ColumnInfo(name = "status") var status : String,
    @ColumnInfo(name = "species") var species : String,
    @ColumnInfo(name = "gender") var gender : String,
    @ColumnInfo(name = "image") var image : String,
    @ColumnInfo(name = "location") var location : String,
)

fun Result.toDatabase() =
    CharacterEntity(
        id,
        name,
        status,
        species,
        gender,
        image,
        location.name
    )