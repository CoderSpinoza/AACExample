package com.kanghara.riiid

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kanghara.riiidproject.domain.PostRepository
import com.kanghara.riiidproject.domain.entities.Comment
import com.kanghara.riiidproject.domain.entities.PatchPost
import com.kanghara.riiidproject.domain.entities.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers

/**
 * @author kevin.kang. Created on 2019-12-03..
 */
class DetailViewModel(val repo: PostRepository) : ViewModel() {
    private val disposables = CompositeDisposable()
    val post = MutableLiveData<Post?>()
    val comments = MutableLiveData<List<Comment>>()
    val edited = MutableLiveData<Boolean>(false)

    fun fetchPostDetail(postId: Int) {
        repo.getPost(postId).subscribeOn(Schedulers.io())
            .zipWith(repo.getComments(postId).subscribeOn(Schedulers.io()))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ (fetchedPost, fetchedComments) ->
                post.value = fetchedPost
                comments.value = fetchedComments
            }, { Log.e("throwable", it.toString()) }).addTo(disposables)
    }


    fun deletePost(postId: Int) {
        repo.deletePost(postId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                post.value = null
            }, {}).addTo(disposables)
    }

    fun editPost(postId: Int, patchPost: PatchPost) {
        repo.patchPost(postId, patchPost)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                edited.value = true
            }, {}).addTo(disposables)
    }

    fun resetEdited() {
        edited.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}