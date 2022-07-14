package com.example.androidmvi.activity.main.helper

import com.example.androidmvi.model.Post
import com.example.androidmvi.network.service.PostService

class MainHelperImpl(private val postService: PostService): MainHelper {
    override suspend fun allPosts(): ArrayList<Post> {
        return postService.allPosts()
    }

    override suspend fun deletePost(id: Int): Post {
        return postService.deletePost(id)
    }
}