// Retrofit interface
package com.example.loginscreen
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import com.example.loginscreen.Api.LoginResult
import com.example.loginscreen.Api.SignupResult
import com.example.loginscreen.activities.Advancedbook
import com.example.loginscreen.models.DefaultResponse
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.sql.Time
import java.util.Date

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
    fun login (
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
