package com.vicryfahreza.msibmovieapp.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vicryfahreza.msibmovieapp.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private var binding:ActivitySignInBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.tvRegister?.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }
        binding?.tvForgotPassword?.setOnClickListener {
            startActivity(Intent(this,ForgetPasswordActivity::class.java))
            finish()
        }
    }
}