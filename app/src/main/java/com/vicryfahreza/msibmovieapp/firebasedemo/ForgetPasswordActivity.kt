package com.vicryfahreza.msibmovieapp.firebasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vicryfahreza.msibmovieapp.R

class ForgetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}