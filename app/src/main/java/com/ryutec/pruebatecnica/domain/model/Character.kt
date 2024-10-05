package com.ryutec.pruebatecnica.domain.model

import android.content.Context
import android.content.Intent
import com.ryutec.pruebatecnica.data.model.rickandmorty.Result
import java.io.Serializable
import androidx.core.content.ContextCompat.startActivity
import com.ryutec.pruebatecnica.data.database.entity.CharacterEntity
import com.ryutec.pruebatecnica.ui.view.CharacterActivity

data class Character (
    val name:String,
    val id:Int,
    val status:String,
    val species:String,
    val gender:String,
    val image:String,
    val location:String
) :Serializable


fun Result.toDomain() =
    Character(
        name,
        id,
        status,
        species,
        gender,
        image,
        location.name
    )

fun CharacterEntity.toDomain() =
    Character(
        name,
        id,
        status,
        species,
        gender,
        image,
        location
    )
fun Character.sendToActivity(context: Context){
    val intent = Intent(context, CharacterActivity::class.java)
    intent.putExtra("character", this)
    startActivity(context,intent,null)
}