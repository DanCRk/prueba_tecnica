package com.ryutec.pruebatecnica.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ryutec.pruebatecnica.R
import com.ryutec.pruebatecnica.databinding.CharacterBinding
import com.ryutec.pruebatecnica.domain.model.Character

class AdapterRickAndMorty(
    private val characters: List<Character>,
    private val onClickListener: (Character) -> Unit,
) : RecyclerView.Adapter<CharacterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(layoutInflater.inflate(R.layout.character, parent, false))
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.binding(characters[position], onClickListener)
    }
}


class CharacterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val bind = CharacterBinding.bind(view)

    fun binding(character: Character,onClickListener: (Character) -> Unit) {

        Glide.with(view).load(character.image).into(bind.image)
        bind.name.text = character.name

        bind.status.text = character.status

        bind.characterLayout.setOnClickListener {
            onClickListener(character)
        }

    }


}