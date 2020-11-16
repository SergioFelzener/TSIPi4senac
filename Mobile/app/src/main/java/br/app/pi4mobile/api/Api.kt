package br.app.pi4mobile.api

import br.app.pi4mobile.models.DefaultResponse
import br.app.pi4mobile.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String
    ):Call<DefaultResponse>


    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email:String,
        @Field("password") password: String,
        @Field("device_name") device_name: String
    ):Call<LoginResponse>
}