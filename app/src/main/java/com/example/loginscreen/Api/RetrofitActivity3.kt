package com.example.loginscreen.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.loginscreen.QuotesApi
import com.example.loginscreen.R
import com.example.loginscreen.Api.Retrofit
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class retrofitActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit3)
        val quotesApi = Retrofit.getInstance().create(QuotesApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = quotesApi.getQuotes()
            if (result != null)
            // Checking the results
                Log.d("ayush: ", result.body().toString())
        }
    }
}