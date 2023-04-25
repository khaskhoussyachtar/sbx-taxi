package com.example.loginscreen.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.example.loginscreen.R

class Booknow_valid : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.booknow_valid)
        val chk =findViewById<CheckBox>(R.id.checkBox)
        val chk1 =findViewById<CheckBox>(R.id.checkBox1)
        val chk2 =findViewById<CheckBox>(R.id.checkBox2)
        val chk3 =findViewById<CheckBox>(R.id.checkBox3)
        val btn1 =findViewById<Button>(R.id.cancel_btn)
        val btn2 =findViewById<Button>(R.id.valid_btn)
        btn1.setOnClickListener(View.OnClickListener {
            if (chk.isChecked){
                Toast.makeText(this, "taxi cleaner", Toast.LENGTH_SHORT).show()
            }
            if (chk1.isChecked){
                Toast.makeText(this, "the fault of the client during the journey", Toast.LENGTH_SHORT).show()}
            if (chk2.isChecked){
                Toast.makeText(this, "client provides the radio station", Toast.LENGTH_SHORT).show()}
            if (chk3.isChecked){
                Toast.makeText(this, "Driver behaves nicely with the  client", Toast.LENGTH_SHORT).show()}
        })
        btn2.setOnClickListener(View.OnClickListener {
            if (chk.isChecked){
                Toast.makeText(this, "taxi cleaner", Toast.LENGTH_SHORT).show()
            }
            if (chk1.isChecked){
                Toast.makeText(this, "the fault of the client during the journey", Toast.LENGTH_SHORT).show()}
            if (chk2.isChecked){
                Toast.makeText(this, "client provides the radio station", Toast.LENGTH_SHORT).show()}
            if (chk3.isChecked){
                Toast.makeText(this, "Driver behaves nicely with the  client", Toast.LENGTH_SHORT).show()}

        })
    }
}