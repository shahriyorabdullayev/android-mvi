package com.example.androidmvi.activity.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidmvi.activity.main.helper.CreateHelper
import com.example.androidmvi.repository.CreateRepository
import com.example.androidmvi.repository.MainRepository

class CreateViewModelFactory(private val mainHelper: CreateHelper): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateViewModel::class.java)) {
            return CreateViewModel(CreateRepository(mainHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}