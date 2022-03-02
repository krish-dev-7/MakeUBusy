package com.example.makeubusy.api

import com.example.makeubusy.models.Activity
import retrofit2.Response
import retrofit2.http.GET

interface ActivityApi {

    @GET("activity")
    suspend fun getActivities(): Response<Activity>
}