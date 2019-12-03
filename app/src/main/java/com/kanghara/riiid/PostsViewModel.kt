package com.kanghara.riiid

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kanghara.riiidproject.domain.PostRepository
import com.kanghara.riiidproject.entities.Comment
import com.kanghara.riiidproject.entities.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers

/**
 * @author kevin.kang. Created on 2019-11-29..
 */
class PostsViewModel(val repo: PostRepository) : ViewModel() {
    private val disposables = CompositeDisposable()
    //    val selectedPost = MutableLiveData<Post>()
    val detailPost = MutableLiveData<Post?>()
    val comments = MutableLiveData<List<Comment>>()

    private val pageSize = 10

    private val sourceFactory = PostsDataSourceFactory(repo, disposables)

    val posts by lazy {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        LivePagedListBuilder<Int, Post>(sourceFactory, config).build()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}