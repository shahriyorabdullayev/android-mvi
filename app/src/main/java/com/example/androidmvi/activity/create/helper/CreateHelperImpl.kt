package com.example.androidmvi.activity.main.helper

import com.example.androidmvi.model.Post
import com.example.androidmvi.network.service.PostService

class CreateHelperImpl(private val postService: PostService): CreateHelper {

    override suspend fun createPost(post: Post): Post {
        return postService.createPost(post)
    }
}