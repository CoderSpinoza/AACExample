package com.kanghara.riiidproject.data.repository

import androidx.room.EmptyResultSetException
import cache.PostDatabase
import com.kanghara.riiidproject.data.api.PostApi
import com.kanghara.riiidproject.data.entities.CommentData
import com.kanghara.riiidproject.data.entities.PatchPostData
import com.kanghara.riiidproject.data.entities.PostData
import com.kanghara.riiidproject.domain.PostRepository
import com.kanghara.riiidproject.domain.entities.Comment
import com.kanghara.riiidproject.domain.entities.PatchPost
import com.kanghara.riiidproject.domain.entities.Post
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * @author kevin.kang. Created on 2019-11-29..
 */
class PostRepositoryImpl(private val api: PostApi, private val db: PostDatabase) : PostRepository {
    override fun getPosts(offset: Int, limit: Int): Observable<List<Post>> =
        Observable.ambArray(
            db.postDao().getAll(offset, limit)
                .flatMap { if (it.isEmpty()) Single.never<List<PostData>>() else Single.just(it) }
                .toObservable().subscribeOn(Schedulers.io()),
            api.getPosts(offset, limit).toObservable().subscribeOn(Schedulers.io())
                .flatMap { db.postDao().insert(it).andThen(Observable.just(it)) }
        ).map { it.map { Post.fromData(it) } }

    override fun getPost(postId: Int): Observable<Post> =
        Observable.concatArrayEager(
            db.postDao().get(postId)
                .onErrorResumeNext {
                    if (it is EmptyResultSetException) {
                        Single.just(PostData(0, 0, "", ""))
                    } else Single.error(it)
                }
                .toObservable().subscribeOn(Schedulers.io()),
            api.getPost(postId).toObservable().subscribeOn(Schedulers.io())
                .flatMap { db.postDao().insert(it).andThen(Observable.just(it)) }
        ).map { Post.fromData(it) }

    override fun getComments(postId: Int): Observable<List<Comment>> =
        Observable.concatArrayEager(
            db.commentDao().getComments(postId)
                .onErrorResumeNext {
                    if (it is EmptyResultSetException) {
                        Single.just<List<CommentData>>(listOf())
                    } else Single.error(it)
                }
                .toObservable()
                .subscribeOn(Schedulers.io()),
            api.getComments(postId).toObservable().subscribeOn(Schedulers.io())
                .map { it.filter { it.postId == postId } }
                .flatMap { db.commentDao().insert(it).andThen(Observable.just(it)) }
        ).map { it.map { Comment.fromData(it) } }


    override fun deletePost(postId: Int): Completable =
        api.deletePost(postId)
//            .concatWith { db.postDao().delete(postId) }

    override fun patchPost(postId: Int, post: PatchPost): Observable<PatchPost> =
        Observable.just(post).map { it.toData() }
            .flatMap { api.patchPost(postId, it).toObservable() }
            .map { PatchPost.fromData(it) }
}

fun Post.Companion.fromData(post: PostData): Post =
    Post(
        post.userId,
        post.id,
        post.title,
        post.body
    )

fun Comment.Companion.fromData(comment: CommentData): Comment =
    Comment(
        comment.postId,
        comment.id,
        comment.name,
        comment.email,
        comment.body
    )

fun PatchPost.Companion.fromData(patchPost: PatchPostData): PatchPost =
    PatchPost(
        patchPost.title,
        patchPost.body
    )

fun PatchPost.toData(): PatchPostData =
    PatchPostData(this.title, this.body)