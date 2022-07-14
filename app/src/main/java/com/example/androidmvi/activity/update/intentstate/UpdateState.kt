package com.example.androidmvi.activity.main.intentstate

import com.example.androidmvi.activity.update.intentstate.UpdateIntent
import com.example.androidmvi.model.Post

sealed class UpdateState {
    object Init : UpdateState()
    object Loading : UpdateState()

    data class UpdatePost(val id: Int, val post: Post) : UpdateState()


    data class Error(val error: String) : UpdateState()


}