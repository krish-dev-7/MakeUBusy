package com.example.makeubusy

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: ActivityApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ActivityApi::class.java)

    }
}

