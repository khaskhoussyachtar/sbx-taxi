// Retrofit interface
package com.example.loginscreen
import com.example.loginscreen.Api.LoginResult
import com.example.loginscreen.Api.SignupResult
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface QuotesApi {
    @FormUrlEncoded
    @POST("/signup")
    suspend fun createUser (
     @Field("fullname") fullname :String,
     @Field("email") email: String,
     @Field("password") password :String,
        @Field("app") app :String="AppTaxi",
    ): SignupResult

    @FormUrlEncoded
    @POST("/login")
    suspend fun login (
        @Field("email") email: String,
        @Field("password") password :String,
        @Field("app") app :String="AppTaxi",
    ): LoginResult

    @GET("/quotes")
 suspend fun getQuotes() : Response <QuoteList>
   @FormUrlEncoded
    @POST("/addBookAdvance")
    fun addbookAdvance (
        @Field("date")  date: String,
        @Field("token")id: String,
        @Field("repeat") repeat:Boolean =false,
        @Field("longitude") long: Long,
        @Field("latitude") latLng: Long,
        @Field("username") username: String,
        @Field("description") description: String,


    ): SignupResult




}
