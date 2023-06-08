package com.example.loginscreen.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.loginscreen.R
import com.example.loginscreen.databinding.ActivityHomeBinding
import com.example.loginscreen.databinding.AdvancedBookBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var reser_immed:ShapeableImageView
    private lateinit var reser_avance:ShapeableImageView
    private lateinit var apropos_reserv:ShapeableImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var name="user"
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //var name="editor"
        //test user//


        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
       // editor.putString("user","")
        //editor.commit()
        val user =sharedPreference.getString("user","")
         if (user.isNullOrEmpty()){
             startActivity(Intent(this, LoginActivity::class.java))
             finish()
             return

         }
        binding.cvConsult.setOnClickListener {
            startActivity(Intent(this,MapsActivity::class.java))
        }
        binding.cvEmergency.setOnClickListener {
        startActivity(Intent(this,Advancedbook::class.java))
        }
        binding.cvReport.setOnClickListener {
            startActivity(Intent(this,AproposActivity::class.java))
        }

    }
    }