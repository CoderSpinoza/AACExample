package com.kanghara.riiid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kanghara.riiid.databinding.ItemPostBinding
import com.kanghara.riiidproject.domain.entities.Post

/**
 * @author kevin.kang. Created on 2019-11-29..
 */
class PostsAdapter :
    PagedListAdapter<Post, PostHolder>(PostsDiffCallback) {
    val observable = MutableLiveData<Pair<ItemPostBinding, Post>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostHolder(ItemPostBinding.inflate(inflater, parent, false))

    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        getItem(position)?.let { post ->
            holder.bind(post)
            holder.binding.titleView.transitionName = "postTitle${post.id}"
            holder.binding.bodyView.transitionName = "postBody${post.id}"
            holder.binding.root.setOnClickListener { view: View ->
                observable.postValue(Pair(holder.binding, post))
            }
        }

    }

    companion object {
        val PostsDiffCallback = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem
        }
    }
}

class PostHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.post = post
    }
}