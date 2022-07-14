package com.example.androidmvi.network.service

import com.example.androidmvi.model.Post
import retrofit2.http.*

interface PostService {

    @Headers(
        "Content-type:application/json"
    )

    @GET("posts")
    suspend fun allPosts(): ArrayList<Post>

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int): Post

    @Headers(
        "Content-type:application/json"
    )
    @POST("posts")
    suspend fun createPost(@Body post: Post): Post


    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body post: Post): Post


}