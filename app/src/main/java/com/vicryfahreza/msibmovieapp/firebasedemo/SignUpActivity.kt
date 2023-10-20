package com.vicryfahreza.msibmovieapp.firebasedemo

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import com.google.firebase.auth.FirebaseAuth
import com.vicryfahreza.msibmovieapp.databinding.ActivitySignUpBinding
import com.vicryfahreza.msibmovieapp.ui.MainActivity

class SignUpActivity : BaseActivity() {
    private var binding:ActivitySignUpBinding? = null
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth = FirebaseAuth.getInstance()

        binding?.tvLoginPage?.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
        }
        binding?.btnSignUp?.setOnClickListener { registerUser() }
    }
    private fun registerUser(){
        val name = binding?.etSinUpName?.text.toString()
        val email = binding?.etSinUpEmail?.text.toString()
        val password = binding?.etSinUpPassword?.text.toString()
        if (validateForm(name,email,password)){
            showProgressBar()
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        showToast(this,"User is created successfully")
                        hideProgressBar()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    showToast(this, "User is not created. Try again later")
                    hideProgressBar()
                }
        }
    }

    private fun validateForm(name: String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name)->{
                binding?.tilName?.error = "Enter name"
                false
            }
            TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()-> {
                binding?.tilEmail?.error = "Enter valid email addres"
                false
            }
            TextUtils.isEmpty(password)->{
                binding?.tilPassword?.error = "Enter password"
                false
            }
            else -> {true}
        }
    }

}