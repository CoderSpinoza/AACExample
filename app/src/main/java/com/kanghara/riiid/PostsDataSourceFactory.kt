package com.kanghara.riiid

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.kanghara.riiidproject.domain.PostRepository
import com.kanghara.riiidproject.domain.entities.Post
import io.reactivex.disposables.CompositeDisposable

/**
 * @author kevin.kang. Created on 2019-11-29..
 */
class PostsDataSourceFactory(
    private val repo: PostRepository,
    private val disposables: CompositeDisposable
) : DataSource.Factory<Int, Post>() {

    val postsDataSourceLiveData = MutableLiveData<PostsDataSource>()

    override fun create(): DataSource<Int, Post> {
        val dataSource = PostsDataSource(repo, disposables)
        postsDataSourceLiveData.postValue(dataSource)
        return dataSource
    }
}