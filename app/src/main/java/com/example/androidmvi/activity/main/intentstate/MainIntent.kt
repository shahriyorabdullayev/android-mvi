package com.example.androidmvi.activity.main.intentstate

sealed class MainIntent {
    object AllPosts: MainIntent()
    object DeletePost: MainIntent()
}