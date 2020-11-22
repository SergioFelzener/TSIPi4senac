package br.app.pi4mobile.api

import br.app.pi4mobile.models.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface Api {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ):Call<DefaultResponse>


    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("device_name") device_name: String
    ):Call<LoginResponse>

    @GET("products")
    fun getProducts():Call<List<Product>>

    @GET("product/{id}")
    fun getProduct(
        @Path("id") id: Int
    ): Call<ProductResponse>

    @FormUrlEncoded
    @PUT("users/{id}")
    fun updateUser(
        @Path("id") id: Int,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ):Call<UpdateResponse>
}