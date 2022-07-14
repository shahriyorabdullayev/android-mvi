package com.example.androidmvi.activity.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidmvi.activity.main.intentstate.CreateIntent
import com.example.androidmvi.activity.main.intentstate.CreateState
import com.example.androidmvi.model.Post
import com.example.androidmvi.repository.CreateRepository
import com.example.androidmvi.repository.MainRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class CreateViewModel(private val createRepository: CreateRepository) : ViewModel() {

    val createIntent = Channel<CreateIntent> { Channel.UNLIMITED }

    private val _state = MutableStateFlow<CreateState>(CreateState.Init)
    val state: StateFlow<CreateState> get() = _state

    var post : Post? = null

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            createIntent.consumeAsFlow().collect {
                when (it) {
                    is CreateIntent.CreatePost -> createPost(it.post)
                }
            }
        }
    }

    private fun createPost(post:Post) {
        viewModelScope.launch {
            _state.value = CreateState.Loading

            _state.value = try {
                CreateState.CreatePost(createRepository.createPost(post))
            } catch (e: Exception) {
                CreateState.Error(e.localizedMessage)
            }
        }
    }

}