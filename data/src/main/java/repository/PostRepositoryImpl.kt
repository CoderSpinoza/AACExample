package com.kanghara.riiidproject.data.repository

import com.kanghara.riiidproject.data.api.PostApi
import com.kanghara.riiidproject.data.entities.PatchPostData
import com.kanghara.riiidproject.domain.PostRepository
import com.kanghara.riiidproject.entities.Comment
import com.kanghara.riiidproject.entities.PatchPost
import com.kanghara.riiidproject.entities.Post
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author kevin.kang. Created on 2019-11-29..
 */
class PostRepositoryImpl(private val api: PostApi) : PostRepository {
    override fun getPosts(page: Int, limit: Int): Single<List<Post>> =
        api.getPosts(page, limit).map {
            it.map { Post.fromData(it) }
        }

    override fun getPost(postId: Int): Single<Post> =
        api.getPost(postId).map { Post.fromData(it) }

    override fun getComments(postId: Int): Single<List<Comment>> =
        api.getComments(postId).map { it.map { Comment.fromData(it) } }


    override fun deletePost(postId: Int): Completable =
        api.deletePost(postId)

    override fun patchPost(postId: Int, post: PatchPost): Single<PatchPost> =
        Single.just(post).map { it.toData() }
            .flatMap { api.patchPost(postId, it) }
            .map { PatchPost.fromData(it) }
}

fun Post.Companion.fromData(post: com.kanghara.riiidproject.data.entities.PostData): Post =
    Post(post.userId, post.id, post.title, post.body)

fun Comment.Companion.fromData(comment: com.kanghara.riiidproject.data.entities.CommentData): Comment =
    Comment(comment.postId, comment.id, comment.name, comment.email, comment.body)

fun PatchPost.Companion.fromData(patchPost: PatchPostData): PatchPost =
    PatchPost(patchPost.title, patchPost.body)

fun PatchPost.toData(): PatchPostData =
    PatchPostData(this.title, this.body)