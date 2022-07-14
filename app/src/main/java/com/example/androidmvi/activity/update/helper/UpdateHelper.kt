package com.example.androidmvi.activity.main.helper

import com.example.androidmvi.model.Post

interface UpdateHelper {

    suspend fun updatePost(id: Int, post: Post): Post
}