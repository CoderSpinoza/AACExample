package com.kanghara.riiidproject.domain

import com.kanghara.riiidproject.domain.entities.Comment
import com.kanghara.riiidproject.domain.entities.PatchPost
import com.kanghara.riiidproject.domain.entities.Post
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * @author kevin.kang. Created on 2019-11-28..
 */
interface PostRepository {
    fun getPosts(offset: Int = 0, limit: Int = 10): Observable<List<Post>>

    fun getPost(postId: Int): Observable<Post>

    fun getComments(postId: Int): Observable<List<Comment>>

    fun deletePost(postId: Int): Completable

    fun patchPost(postId: Int, post: PatchPost): Observable<PatchPost>
}