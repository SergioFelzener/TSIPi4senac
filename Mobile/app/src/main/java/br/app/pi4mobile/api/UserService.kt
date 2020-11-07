package br.app.pi4mobile.api

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @GET("users")
    fun list(): Call<List<UserModel>>

    @POST("register")
    fun registerUser(
        @Body user: JSONObject
    ): Call<UserModel>

}