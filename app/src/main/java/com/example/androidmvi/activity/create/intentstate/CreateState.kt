package com.example.androidmvi.activity.main.intentstate

import com.example.androidmvi.model.Post

sealed class CreateState {
    object Init: CreateState()
    object Loading: CreateState()

    data class CreatePost(val post: Post): CreateState()

    data class Error(val error: String): CreateState()



}