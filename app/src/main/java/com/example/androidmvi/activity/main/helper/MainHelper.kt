package com.example.androidmvi.activity.main.helper

import com.example.androidmvi.model.Post

interface MainHelper {

    suspend fun allPosts(): ArrayList<Post>

    suspend fun deletePost(id: Int): Post
}