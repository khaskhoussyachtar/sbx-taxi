// Retrofit interface
package com.example.loginscreen
import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.loginscreen.models.DefaultResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
interface QuotesApi {
    @FormUrlEncoded
    @POST("createuser")
    fun createUser (
     @Field("fullname") fullname :String,
     @Field("email") email: String,
     @Field("password") password :String,
    ): Response<Boolean>

    @FormUrlEncoded
    @POST("login")
    fun login (
        @Field("email") email: String,
        @Field("password") password :String,
    ): Response<Boolean>

    @GET("/quotes")
 suspend fun getQuotes() : Response <QuoteList>


}
