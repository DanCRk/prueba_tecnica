package com.ryutec.pruebatecnica.ui.view

import android.os.Build
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.ryutec.pruebatecnica.R
import com.ryutec.pruebatecnica.databinding.ActivityCharacterBinding
import com.ryutec.pruebatecnica.domain.model.Character
import jp.wasabeef.glide.transformations.BlurTransformation


class CharacterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCharacterBinding
    private lateinit var character: Character
    private var zoom = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCharacterImage()

        zoomWallpaper()

        close()

        setCharacterInfo()

    }

    private fun close() {
        binding.backButton.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setCharacterInfo(){

        binding.location.text = "Location: ${character.location}"
        binding.gender.text = "Gender: ${character.gender}"
        binding.species.text = "Species: ${character.species}"
        binding.status.text = "Status: ${character.status}"

        binding.textName.text = character.name

        binding.textId.text = "id: ${character.id}"

    }


    private fun setCharacterImage() {
        character = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("character", Character::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("character") as Character
        }

        Glide
            .with(this)
            .load(character.image)
            .apply(bitmapTransform(BlurTransformation(30, 5)))
            .into(binding.imgBack)

        Glide
            .with(this)
            .load(character.image)
            .into(binding.imgMain)
    }

    private fun zoomWallpaper() {
        binding.card.setOnClickListener {
            zoom = if (zoom) {
                val animZoomIn = AnimationUtils.loadAnimation(
                    this,
                    R.anim.zoom_in
                )
                animationMoveIcons(false)
                binding.card.startAnimation(animZoomIn)
                false
            } else {
                val animZoomOut = AnimationUtils.loadAnimation(
                    this,
                    R.anim.zoom_out
                )
                animationMoveIcons(true)
                binding.card.startAnimation(animZoomOut)
                true
            }
        }
    }

    private fun animationMoveIcons(inOut: Boolean) {
        if (inOut) {
            val animation = TranslateAnimation(
                0.0f, 0.0f,
                100.0f, 0.0f
            )
            animation.fillAfter = true
            animation.duration = 400
            binding.containerIcons.startAnimation(animation)
        } else {
            val animation = TranslateAnimation(
                0.0f, 0.0f,
                0.0f, 100.0f
            )
            animation.fillAfter = true
            animation.duration = 400
            binding.containerIcons.startAnimation(animation)
        }

    }
}