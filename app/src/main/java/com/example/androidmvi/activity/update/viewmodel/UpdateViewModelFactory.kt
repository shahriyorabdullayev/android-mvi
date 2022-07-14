package com.example.androidmvi.activity.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidmvi.activity.main.helper.CreateHelper
import com.example.androidmvi.activity.main.helper.UpdateHelper
import com.example.androidmvi.repository.MainRepository
import com.example.androidmvi.repository.UpdateRepository

class UpdateViewModelFactory(private val updateHelper: UpdateHelper): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateViewModel::class.java)) {
            return UpdateViewModel(UpdateRepository(updateHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}