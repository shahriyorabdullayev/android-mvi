package com.example.androidmvi.activity.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidmvi.R
import com.example.androidmvi.activity.main.helper.CreateHelperImpl
import com.example.androidmvi.activity.main.intentstate.CreateIntent
import com.example.androidmvi.activity.main.intentstate.CreateState
import com.example.androidmvi.activity.main.intentstate.MainState
import com.example.androidmvi.activity.main.viewmodel.CreateViewModel
import com.example.androidmvi.activity.main.viewmodel.CreateViewModelFactory
import com.example.androidmvi.databinding.ActivityCreateBinding
import com.example.androidmvi.model.Post
import com.example.androidmvi.network.RetrofitBuilder
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.random.Random

class CreateActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCreateBinding.inflate(layoutInflater) }

    private lateinit var viewModel: CreateViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()

        binding.apply {

            btnSave.setOnClickListener {
                if (etTitle.text.isNotEmpty() && etBody.text.isNotEmpty()) {
                    val title = etTitle.text.toString()
                    val body = etBody.text.toString()

                    val post = Post(title = title, body = body, userId = Random.nextInt())
                    createPost(post)

                }

            }

        }

        observeViewModel()


    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is CreateState.Init -> Log.d("CreateActivity", "Init")
                    is CreateState.Loading -> {
                        Log.d("CreateActivity", "Loading")
                        binding.progress.visibility = View.VISIBLE
                    }
                    is CreateState.CreatePost -> {
                        binding.progress.visibility = View.GONE
                        Log.d("CreateActivity", "${it.post}")
                        finish()

                    }
                    is CreateState.Error -> Log.d("CreateActivity", "DeletePost" + it.error)
                }
            }
        }
    }

    private fun createPost(post: Post) {
        lifecycleScope.launch {
            viewModel.createIntent.send(CreateIntent.CreatePost(post))
        }

    }

    private fun setupViewModel() {
        val factory = CreateViewModelFactory(CreateHelperImpl(RetrofitBuilder.POST_SERVICE))
        viewModel = ViewModelProvider(this, factory)[CreateViewModel::class.java]
    }
}