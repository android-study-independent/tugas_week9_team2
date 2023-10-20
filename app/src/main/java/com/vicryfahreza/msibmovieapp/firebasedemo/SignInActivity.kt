package com.vicryfahreza.msibmovieapp.firebasedemo

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import com.google.firebase.auth.FirebaseAuth
import com.vicryfahreza.msibmovieapp.databinding.ActivitySignInBinding
import com.vicryfahreza.msibmovieapp.ui.MainActivity

class SignInActivity : BaseActivity() {
    private var binding:ActivitySignInBinding?=null
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth = FirebaseAuth.getInstance()

        binding?.tvRegister?.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }
        binding?.tvForgotPassword?.setOnClickListener {
            startActivity(Intent(this,ForgetPasswordActivity::class.java))
            finish()
        }
        binding?.btnSignIn?.setOnClickListener {
            signInUser()
        }
    }
    private fun signInUser()
    {
        val email = binding?.etSinInEmail?.text.toString()
        val password = binding?.etSinInPassword?.text.toString()
        if(validateForm(email,password))
        {
            showProgressBar()
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful)
                    {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                        hideProgressBar()
                    }
                    else
                    {
                        showToast(this,"Cannot login successfully. Try after sometinc")
                        hideProgressBar()
                    }
                }
        }
    }
    private fun validateForm(email: String, password: String): Boolean {
        return when {
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