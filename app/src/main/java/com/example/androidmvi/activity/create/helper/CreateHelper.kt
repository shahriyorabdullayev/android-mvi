package com.example.androidmvi.activity.main.helper

import com.example.androidmvi.model.Post

interface CreateHelper {

    suspend fun createPost(post: Post): Post
}