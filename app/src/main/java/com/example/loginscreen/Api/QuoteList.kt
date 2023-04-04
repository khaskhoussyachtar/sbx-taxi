//data class
// according to JSON response
package com.example.loginscreen
data class QuoteList(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results:  List<com.example.loginscreen.Api.Result>,
    val totalCount: Int,
    val totalPages: Int
)