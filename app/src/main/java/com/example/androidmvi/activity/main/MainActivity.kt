package com.example.androidmvi.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmvi.R
import com.example.androidmvi.activity.create.CreateActivity
import com.example.androidmvi.activity.main.helper.CreateHelperImpl
import com.example.androidmvi.activity.main.helper.MainHelperImpl
import com.example.androidmvi.activity.main.intentstate.CreateIntent
import com.example.androidmvi.activity.main.intentstate.CreateState
import com.example.androidmvi.activity.main.intentstate.MainIntent
import com.example.androidmvi.activity.main.intentstate.MainState
import com.example.androidmvi.activity.main.viewmodel.CreateViewModel
import com.example.androidmvi.activity.main.viewmodel.CreateViewModelFactory
import com.example.androidmvi.activity.main.viewmodel.MainViewModel
import com.example.androidmvi.activity.main.viewmodel.MainViewModelFactory
import com.example.androidmvi.adapter.PostAdapter
import com.example.androidmvi.databinding.ActivityMainBinding
import com.example.androidmvi.model.Post
import com.example.androidmvi.network.RetrofitBuilder
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var recyclerView: RecyclerView

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()

        binding.ivAddPost.setOnClickListener {
            startActivity(Intent(this, CreateActivity::class.java))
        }


        observeViewModel()

    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when(it) {
                    is MainState.Init -> Log.d("MainActivity", "Init")
                    is MainState.Loading -> {
                        binding.progress.visibility = View.VISIBLE

                        Log.d("MainActivity", "Loading")
                    }
                    is MainState.AllPosts -> {
                        binding.progress.visibility = View.GONE
                        Log.d("MainActivity", "${it.posts}")
                        refreshAdapter(it.posts)
                    }
                    is MainState.DeletePost -> {
                        binding.progress.visibility = View.GONE
                        Log.d("MainActivity", "${it.post}")

                        intentAllPosts()
                    }
                    is MainState.Error -> Log.d("MainActivity", "DeletePost"+ it.error)
                }
            }
        }
    }

    private fun refreshAdapter(posts: ArrayList<Post>) {
        val adapter = PostAdapter(this, posts)
        binding.recyclerView.adapter = adapter

    }

    private fun initViews() {
        val factory = MainViewModelFactory(MainHelperImpl(RetrofitBuilder.POST_SERVICE))
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        recyclerView = findViewById(R.id.recyclerView)
        
        intentAllPosts()



    }

    private fun intentAllPosts() {
        lifecycleScope.launch {
            viewModel.mainIntent.send(MainIntent.AllPosts)
        }
    }

    fun intentDeletePost(id: Int) {
        viewModel.postId = id
        lifecycleScope.launch {
            viewModel.mainIntent.send(MainIntent.DeletePost)
        }
    }
}