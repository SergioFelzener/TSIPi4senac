package br.app.pi4mobile.api

import br.app.pi4mobile.models.*
import br.app.pi4mobile.models.response.CategoryResponse
import br.app.pi4mobile.models.response.DefaultResponse
import br.app.pi4mobile.models.response.LoginResponse
import br.app.pi4mobile.models.response.ProductResponse
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
    @PUT("updateUser/{id}")
    fun updateUser(
        @Path("id") id: Int,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ):Call<User>

    @GET("cart")
    fun getCartItems(): Call<List<Product>>

    @DELETE("remove-prod/{id}")
     fun deleteCartItem(): Void

    
    @GET("categories")
    fun getCategories():Call<List<Category>>

    @GET("category/{id}")
    fun getCategory(
        @Path("id") id: Int
    ): Call<CategoryResponse>
}