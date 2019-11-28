import io.reactivex.Single

/**
 * @author kevin.kang. Created on 2019-11-28..
 */
interface PostRepository {
    fun getPosts(page: Int = 0, limit: Int = 10): Single<List<Post>>

    fun getPost(postId: Int): Single<Post>

    fun getComments(postId: Int): Single<List<Comment>>
}