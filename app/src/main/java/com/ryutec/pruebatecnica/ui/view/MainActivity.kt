package com.ryutec.pruebatecnica.ui.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.ryutec.pruebatecnica.R
import com.ryutec.pruebatecnica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavController()
    }

    private fun setNavController() {
        // Asignar el bottom nav
        val btmNav = binding.bottomNavigation
        // Instanciar un controllador
        val navControler = Navigation.findNavController(this, R.id.frag_host)
        // Settear el controlador a nuestro nav
        NavigationUI.setupWithNavController(btmNav, navControler)
    }
}