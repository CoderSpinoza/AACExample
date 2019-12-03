package com.kanghara.riiid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kanghara.riiid.databinding.ItemCommentBinding
import com.kanghara.riiidproject.domain.entities.Comment

/**
 * @author kevin.kang. Created on 2019-12-03..
 */
class CommentsAdapter(var comments: List<Comment>) : RecyclerView.Adapter<CommentHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentHolder(binding)
    }

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        holder.bind(comments[position])
    }
}

class CommentHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(comment: Comment) {
        binding.comment = comment
    }
}