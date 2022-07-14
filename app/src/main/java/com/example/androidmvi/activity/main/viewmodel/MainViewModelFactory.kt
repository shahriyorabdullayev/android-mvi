package com.example.androidmvi.activity.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidmvi.activity.main.helper.MainHelper
import com.example.androidmvi.repository.MainRepository

class MainViewModelFactory(private val mainHelper: MainHelper): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(mainHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}