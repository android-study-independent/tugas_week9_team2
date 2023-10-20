package com.vicryfahreza.msibmovieapp.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vicryfahreza.msibmovieapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private var binding:ActivitySignUpBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.tvLoginPage?.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
        }
    }
}