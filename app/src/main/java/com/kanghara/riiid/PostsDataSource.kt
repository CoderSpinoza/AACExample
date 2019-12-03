package com.kanghara.riiid

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import com.kanghara.riiidproject.domain.PostRepository
import com.kanghara.riiidproject.domain.entities.Post
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

/**
 * @author kevin.kang. Created on 2019-11-29..
 */
class PostsDataSource(
    private val postRepo: PostRepository,
    private val disposables: CompositeDisposable
) :
    ItemKeyedDataSource<Int, Post>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Post>) {
        postRepo.getPosts(0, params.requestedLoadSize)
            .subscribe({ posts ->
                callback.onResult(posts)
            }) { error -> }
            .addTo(disposables)
    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Post>) {
        postRepo.getPosts(params.key, params.requestedLoadSize)
            .subscribe({ posts ->
                callback.onResult(posts)
            }) { error -> }
            .addTo(disposables)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Post>) {
    }

    override fun getKey(item: Post): Int = item.id
}