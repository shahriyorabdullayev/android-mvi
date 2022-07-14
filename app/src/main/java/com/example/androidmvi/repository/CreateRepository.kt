package com.example.androidmvi.repository

import com.example.androidmvi.activity.main.helper.CreateHelper
import com.example.androidmvi.activity.main.helper.MainHelper
import com.example.androidmvi.model.Post

class CreateRepository(private val createHelper: CreateHelper) {


    suspend fun createPost(post: Post) = createHelper.createPost(post)

}