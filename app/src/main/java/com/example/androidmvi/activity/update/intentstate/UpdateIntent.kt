package com.example.androidmvi.activity.update.intentstate

import com.example.androidmvi.model.Post

sealed class UpdateIntent {

    data class UpdatePost(val id: Int, val post: Post): UpdateIntent()

}