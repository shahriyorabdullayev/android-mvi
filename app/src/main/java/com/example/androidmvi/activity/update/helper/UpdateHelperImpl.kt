package com.example.androidmvi.activity.main.helper

import com.example.androidmvi.model.Post
import com.example.androidmvi.network.service.PostService

class UpdateHelperImpl(private val postService: PostService): UpdateHelper {
    override suspend fun updatePost(id: Int, post: Post): Post {
        return postService.updatePost(id, post)
    }

}