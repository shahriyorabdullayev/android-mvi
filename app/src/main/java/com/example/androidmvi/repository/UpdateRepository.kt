package com.example.androidmvi.repository

import com.example.androidmvi.activity.main.helper.CreateHelper
import com.example.androidmvi.activity.main.helper.MainHelper
import com.example.androidmvi.activity.main.helper.UpdateHelper
import com.example.androidmvi.model.Post

class UpdateRepository(private val updateHelper: UpdateHelper) {

    suspend fun updatePost(id: Int, post: Post) = updateHelper.updatePost(id, post)

}