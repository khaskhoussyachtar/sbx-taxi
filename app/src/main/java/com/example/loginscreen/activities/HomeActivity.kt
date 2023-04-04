package com.example.loginscreen.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginscreen.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var name="user"
        //var name="editor"
        //test user//

        setContentView(R.layout.activity_home)
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
    }
    }