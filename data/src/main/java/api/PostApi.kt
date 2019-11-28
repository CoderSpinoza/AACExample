package com.kanghara.riiidproject.data.api

import com.kanghara.riiidproject.data.entities.Comment
import com.kanghara.riiidproject.data.entities.PatchPost
import com.kanghara.riiidproject.data.entities.Post
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

/**
 * @author kevin.kang. Created on 2019-11-28..
 */
interface PostApi {
    @GET("/posts")
    fun getPosts(@Query("_start") page: Int = 0, @Query("_limit") limit: Int = 10): Single<List<Post>>

    @GET("/posts/{postId}")
    fun getPost(@Path("postId") postId: Int): Single<Post>

    @GET("/posts/{postId}/comments")
    fun getComments(@Path("postId") postId: Int): Single<List<Comment>>

    @DELETE("/posts/{postId}")
    fun deletePost(@Path("postId") postId: Int): Completable

    @PATCH("/posts/{postId}")
    fun patchPost(@Path("postId") postId: Int, @Body post: PatchPost): Single<PatchPost>
}