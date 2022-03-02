package com.example.makeubusy.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.makeubusy.models.Activity
import com.example.makeubusy.utils.Resource
import com.example.makeubusy.viewModel.repository.MyRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MyViewModel(val myRep: MyRepository): ViewModel() {
    val activities : MutableLiveData<Resource<Activity>> = MutableLiveData()
    fun getActivitiesVM(){
        viewModelScope.launch{
            activities.postValue(Resource.Loading())
            val resp = myRep.getActivitiesR()
            activities.postValue(handleResponse(resp))
        }
    }
    private fun handleResponse(response: Response<Activity>): Resource<Activity> {
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())

    }

}