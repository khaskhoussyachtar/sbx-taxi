package com.example.loginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.loginscreen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
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
                Toast.makeText(this, "${binding.editTextTextEmailAddress.text}is logged in !!", Toast.LENGTH_SHORT).show()
            }



        }
        binding.tvHaventAccount.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }
}
