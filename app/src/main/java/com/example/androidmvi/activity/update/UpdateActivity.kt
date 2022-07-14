package com.example.androidmvi.activity.update

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidmvi.activity.main.helper.CreateHelperImpl
import com.example.androidmvi.activity.main.helper.UpdateHelperImpl
import com.example.androidmvi.activity.main.intentstate.CreateState
import com.example.androidmvi.activity.main.intentstate.UpdateState
import com.example.androidmvi.activity.main.viewmodel.CreateViewModel
import com.example.androidmvi.activity.main.viewmodel.CreateViewModelFactory
import com.example.androidmvi.activity.main.viewmodel.UpdateViewModel
import com.example.androidmvi.activity.main.viewmodel.UpdateViewModelFactory
import com.example.androidmvi.activity.update.intentstate.UpdateIntent
import com.example.androidmvi.databinding.ActivityUpdateBinding
import com.example.androidmvi.model.Post
import com.example.androidmvi.network.RetrofitBuilder
import kotlinx.coroutines.launch
import kotlin.random.Random

class UpdateActivity : AppCompatActivity() {

    private lateinit var viewModel: UpdateViewModel
    private val binding by lazy { ActivityUpdateBinding.inflate(layoutInflater) }

    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()

        val post = intent.getSerializableExtra("post") as Post

        binding.etBody.setText(post.body)
        binding.etTitle.setText(post.title)
        id = post.id!!

        binding.apply {

            btnUpdate.setOnClickListener {
                if (etBody.text.isNotEmpty() && etTitle.text.isNotEmpty()) {
                    val post = Post(title = etTitle.text.toString(), body = etBody.text.toString(), userId = Random.nextInt())
                    updatePost(id, post)
                }
            }
        }

        observeViewModel()
    }

    private fun updatePost(id: Int, post: Post) {
        lifecycleScope.launch {
            viewModel.updateIntent.send(UpdateIntent.UpdatePost(id, post))
        }

    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is UpdateState.Init -> Log.d("UpdateActivity", "Init")
                    is UpdateState.Loading -> {
                        binding.progress.visibility = View.VISIBLE

                        Log.d("UpdateActivity", "Loading")
                    }
                    is UpdateState.UpdatePost -> {
                        binding.progress.visibility = View.GONE
                        Log.d("UpdateActivity", "${it.post}")
                        finish()
                    }
                    is UpdateState.Error -> Log.d("UpdateActivity", "DeletePost" + it.error)
                }
            }
        }

    }

    private fun setupViewModel() {
        val factory = UpdateViewModelFactory(UpdateHelperImpl(RetrofitBuilder.POST_SERVICE))
        viewModel = ViewModelProvider(this, factory)[UpdateViewModel::class.java]
    }
}