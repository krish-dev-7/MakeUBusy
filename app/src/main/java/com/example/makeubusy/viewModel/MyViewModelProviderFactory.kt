package com.example.makeubusy.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.makeubusy.viewModel.repository.MyRepository

class MyViewModelProviderFactory(val rep: MyRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(rep) as T
    }
}