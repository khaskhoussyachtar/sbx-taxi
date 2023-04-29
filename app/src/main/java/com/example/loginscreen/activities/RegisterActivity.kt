package com.example.loginscreen.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginscreen.Api.Retrofit
import com.example.loginscreen.QuotesApi
import com.example.loginscreen.databinding.ActivityRegisterBinding
import kotlinx.coroutines.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val fullname = binding.editTextTextfullname.text.toString().trim()
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()
            if (fullname.isEmpty()) {
                binding.editTextTextfullname.error = "fullname required"
                binding.editTextTextfullname.requestFocus()
                return@setOnClickListener

            }
            if (email.isEmpty()) {
                binding.editTextTextEmailAddress.error = "email required"
                binding.editTextTextEmailAddress.requestFocus()
                return@setOnClickListener


            }
            if (fullname.isEmpty()) {
                binding.editTextTextPassword.error = "password required"
                binding.editTextTextPassword.requestFocus()
                return@setOnClickListener

                // setContentView(R.layout.activity_register)//
            }
            val client = Retrofit.getInstance().create(QuotesApi::class.java)
            GlobalScope.launch(Dispatchers.IO) {
                val result=client.createUser(fullname, email, password)
                withContext(Dispatchers.Main) {

                    if (result != null && result.res){

                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))

                    }else{
                        Toast.makeText(applicationContext, "no server reponse", Toast.LENGTH_SHORT).show()

                    }
                }


            }
        }
    }
}