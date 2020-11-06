package br.app.pi4mobile.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
                        .baseUrl("http://localhost:8000/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

    fun userService () : UserService {

        return retrofit.create(UserService::class.java)

    }
}
