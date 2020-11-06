package br.app.pi4mobile.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @GET("users")
    fun list(): Call<List<UserModel>>

    @POST("login")
    fun loginUser(
        @Body usermodel: UserModel
    ): Call<UserModel>

}