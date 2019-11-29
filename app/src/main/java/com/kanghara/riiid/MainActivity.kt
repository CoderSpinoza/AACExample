package com.kanghara.riiid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.kanghara.riiidproject.entities.Post
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val postsViewModel: PostsViewModel by viewModel()
    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postsAdapter = PostsAdapter()

        postsRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        postsRecyclerView.adapter = postsAdapter
        postsViewModel.posts.observe(this, Observer { posts -> postsAdapter.submitList(posts) })
    }
}
