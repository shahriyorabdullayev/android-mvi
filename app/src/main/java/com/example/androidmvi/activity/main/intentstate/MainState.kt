package com.example.androidmvi.activity.main.intentstate

import com.example.androidmvi.model.Post

sealed class MainState {
    object Init: MainState()
    object Loading: MainState()

    data class AllPosts(val posts: ArrayList<Post>): MainState()
    data class DeletePost(val post: Post): MainState()

    data class Error(val error: String): MainState()



}