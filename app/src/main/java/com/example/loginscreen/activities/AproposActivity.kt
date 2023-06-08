package com.example.loginscreen.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.loginscreen.R
import com.example.loginscreen.apropos2Activity

class AproposActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apropos)
        val button = findViewById<Button>(R.id.next)
        button.setOnClickListener {
            val intent =Intent(this, apropos2Activity::class.java)
            startActivity(intent)
        }

    }
}