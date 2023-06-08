package com.example.loginscreen.Api

import com.example.loginscreen.models.DefaultResponse

data class Result(
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val tags: List<String>
)
data class SignupResult(
    val res:Boolean,
    val response: String,

)
data class LoginResult(
    val res:Boolean,
    val response: String,
    val token: String,
    val fullname: String,
)