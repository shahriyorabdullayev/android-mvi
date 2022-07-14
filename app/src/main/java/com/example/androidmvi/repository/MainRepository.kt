package com.example.androidmvi.repository

import com.example.androidmvi.activity.main.helper.CreateHelper
import com.example.androidmvi.activity.main.helper.MainHelper

class MainRepository(private val mainHelper: MainHelper) {

    suspend fun allPosts() = mainHelper.allPosts()

    suspend fun deletePost(id: Int) = mainHelper.deletePost(id)

}