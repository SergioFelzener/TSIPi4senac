package br.app.pi4mobile.api

import br.app.pi4mobile.models.*
import br.app.pi4mobile.models.response.*
import retrofit2.Call
import retrofit2.http.*
import java.time.temporal.TemporalAmount


interface Api {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<DefaultResponse>


    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("device_name") device_name: String
    ): Call<LoginResponse>

    @GET("products")
    fun getProducts(): Call<List<Product>>

    @GET("product/{id}")
    fun getProduct(
        @Path("id") id: Int
    ): Call<ProductResponse>

    @FormUrlEncoded
    @PUT("updateUser/{id}")
    fun updateUser(
        @Path("id") id: Int?,
        @Field("name") name: String?,
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Call<UpdateResponse>

    @GET("cart")
    fun getCartItems(): Call<CartResponse>

    @DELETE("remove-prod/{id}")
    fun deleteCartItem(): Void

    @GET("categories")
    fun getCategories(): Call<List<Category>>

    @GET("category/{id}")
    fun getCategory(
        @Path("id") id: Int
    ): Call<CategoryResponse>

    @FormUrlEncoded
    @POST("remove-prod")
    fun removeProd(
        @Field("product_id") product_id: Int
    ): Call<RemoveProdResponse>

    @FormUrlEncoded
    @POST("remove-prod-one")
    fun removeProdOne(
        @Field("product_id") product_id: Int
    ): Call<RemoveProdResponse>

    @FormUrlEncoded
    @POST("cart")
    fun addProductCart(
        @Field("product_id") product_id: Int?,
        @Field("amount") amount: Int
    ): Call<AddProdResponse>


    @GET("orders")
    fun getOrders(): Call<OrdersResponse>

    @GET("order-products/{id}")
    fun getOrder(@Path("id")id: Int): Call<OrderResponse>

    @FormUrlEncoded
    @POST("checkout")
    fun checkout(
        @Field("total") total: Double): Call<CheckoutResponse>
}
