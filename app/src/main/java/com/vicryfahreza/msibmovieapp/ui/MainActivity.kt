package com.vicryfahreza.msibmovieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vicryfahreza.msibmovieapp.R
import com.vicryfahreza.msibmovieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_main)
    }
}