package com.example.androidmvi.activity.main.intentstate

import com.example.androidmvi.model.Post

sealed class CreateIntent {
    data class CreatePost(val post: Post): CreateIntent()
}