package com.example.androidmvi.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmvi.R
import com.example.androidmvi.activity.main.MainActivity
import com.example.androidmvi.activity.update.UpdateActivity
import com.example.androidmvi.model.Post
import com.example.androidmvi.utils.Utils

class PostAdapter(var activity: MainActivity, var items: ArrayList<Post>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onClick: ((Post) -> Unit)? = null

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post_list, parent, false)
        return PosterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post: Post = items[position]
        if (holder is PosterViewHolder) {
            val ll_poster = holder.ll_poster
            val tv_title = holder.tv_title
            val tv_body = holder.tv_body

            tv_title.setText(post.title?.toUpperCase())
            tv_body.setText(post.body)

            ll_poster.setOnLongClickListener {
                deletePostDialog(post)
                true
            }

            ll_poster.setOnClickListener {
                val intent = Intent(activity, UpdateActivity::class.java)
                intent.putExtra("post", post)
                activity.startActivity(intent)
            }
        }
    }

    inner class PosterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_title: TextView
        var tv_body: TextView
        var ll_poster: LinearLayout

        init {
            ll_poster = view.findViewById(R.id.ll_poster)
            tv_title = view.findViewById(R.id.tv_title)
            tv_body = view.findViewById(R.id.tv_body)
        }
    }

    private fun deletePostDialog(post: Post) {
        val title = "Delete"
        val body = "Do you want to delete?"
        Utils.customDialog(activity, title, body, object : Utils.DialogListener {
            override fun onPositiveClick() {
                activity.intentDeletePost(post.id!!)
            }

            override fun onNegativeClick() {

            }
        })
    }
}