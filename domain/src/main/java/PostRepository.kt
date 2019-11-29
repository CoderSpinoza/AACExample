package com.kanghara.riiidproject.domain

import com.kanghara.riiidproject.entities.Comment
import com.kanghara.riiidproject.entities.PatchPost
import com.kanghara.riiidproject.entities.Post
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author kevin.kang. Created on 2019-11-28..
 */
interface PostRepository {
    fun getPosts(page: Int = 0, limit: Int = 10): Single<List<Post>>

    fun getPost(postId: Int): Single<Post>

    fun getComments(postId: Int): Single<List<Comment>>

    fun deletePost(postId: Int): Completable

    fun patchPost(postId: Int, post: PatchPost): Single<PatchPost>
}