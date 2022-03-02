package com.example.makeubusy.api


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(logging).build()
            Retrofit.Builder()
                .baseUrl("https://boredapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        }
        val api: ActivityApi by lazy{
            retrofit.create(ActivityApi::class.java)
        }
    }
}

