package com.example.loginscreen.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.loginscreen.Api.Retrofit
import com.example.loginscreen.QuotesApi
import com.example.loginscreen.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var Username: EditText
    lateinit var Password: EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)//
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            Log.i("achtar", "email:${binding.editTextTextEmailAddress} ")
            Log.i("achtar", "password:${binding.editTextTextPassword} ")
            if (binding.editTextTextPassword.text.isNullOrBlank()&&binding.editTextTextEmailAddress.text.isNullOrBlank()) {
                Toast.makeText(this, "please fill the required fields", Toast.LENGTH_SHORT)
                    .show()
            }
            else{
                val client = Retrofit.getInstance().create(QuotesApi::class.java)
                val email = binding.editTextTextEmailAddress.text.toString().trim()
                val password = binding.editTextTextPassword.text.toString().trim()

                GlobalScope.launch(Dispatchers.IO) {

                    val result=client.login(email, password)
                    withContext(Dispatchers.Main) {
                        if (result != null){

                            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)
                            val editor = sharedPreference.edit()
                            editor.putString("user",result.token)
                            editor.putString("username",result.fullname)
                            editor.apply()

                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))

                        }else{
                            Toast.makeText(applicationContext, "no server reponse", Toast.LENGTH_SHORT).show()

                        }
                    }


                }


                // Toast.makeText(this, "${binding.editTextTextEmailAddress.text}is logged in !!", Toast.LENGTH_SHORT).show()//
            }



        }
        binding.tvHaventAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
