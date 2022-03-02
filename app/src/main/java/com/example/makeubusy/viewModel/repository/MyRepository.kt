package com.example.makeubusy.viewModel.repository

import com.example.makeubusy.api.RetrofitInstance
import com.example.makeubusy.models.Activity
import retrofit2.Response


class MyRepository {

    suspend fun getActivitiesR(): Response<Activity> {
        return RetrofitInstance.api.getActivities()
    }

}
